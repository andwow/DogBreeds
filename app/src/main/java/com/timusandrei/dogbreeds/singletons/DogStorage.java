package com.timusandrei.dogbreeds.singletons;

import android.util.Xml;

import com.timusandrei.dogbreeds.R;
import com.timusandrei.dogbreeds.models.Dog;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DogStorage {

    private static DogStorage instance;

    private static InputStream xmlFile;

    private List<Dog> dogs;

    private static final String ns = null;

    public List<Dog> getDogs() {
        return dogs;
    }

    private DogStorage() {
        if(xmlFile!=null) {
            try {
                dogs = parse(xmlFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void setXmlFile(InputStream xmlFile) {
        DogStorage.xmlFile = xmlFile;
    }

    public static DogStorage getInstance() {
        if(instance == null) {
            instance = new DogStorage();
        }
        return instance;
    }

    public List<Dog> parse(InputStream in) throws IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return null;
    }

    private List<Dog> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Dog> entries = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "dogs");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("dog")) {
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private Dog readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {

        final String trainable = "trainable";
        final String apartmentLiving = "apartmentLiving";
        final String firstTimeDog = "firstTimeDog";
        final String familyDog = "familyDog";

        parser.require(XmlPullParser.START_TAG, ns, "dog");
        int id = 1;
        String breedName = null;
        String shortDescription = null;
        String longDescription = null;
        String wikiUrl = null;
        float apartmentLivingRating = 0;
        float familyDogRating = 0;
        float trainableRating = 0;
        float firstTimeDogRating = 0;
        int image = R.drawable.border_collie;
        while (parser.next() != XmlPullParser.END_TAG) {

            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            switch (name) {
                case "id":
                    id = readId(parser);
                    break;
                case "name":
                    breedName = readName(parser);
                    break;
                case "shortDescription":
                    shortDescription = readShortDescription(parser);
                    break;
                case "longDescription":
                    longDescription = readLongDescription(parser);
                    break;
                case "wikiUrl":
                    wikiUrl = readWikiUrl(parser);
                    break;
                case "image":
                    image = readImage(parser);
                    break;
                case apartmentLiving:
                    apartmentLivingRating = readRating(parser, apartmentLiving);
                    break;
                case familyDog:
                    familyDogRating = readRating(parser, familyDog);
                    break;
                case trainable:
                    trainableRating = readRating(parser, trainable);
                    break;
                case firstTimeDog:
                    firstTimeDogRating = readRating(parser, firstTimeDog);
                    break;
                default:
                    skip(parser);
                    break;
            }
        }
        return new Dog(id, breedName, shortDescription, longDescription, wikiUrl, image, apartmentLivingRating, familyDogRating, trainableRating, firstTimeDogRating);
    }

    private int readId(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "id");
        int id = Integer.parseInt(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, "id");
        return id;
    }

    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return name;
    }

    private String readShortDescription(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "shortDescription");
        String description = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "shortDescription");
        return description;
    }

    private String readLongDescription(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "longDescription");
        String description = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "longDescription");
        return description;
    }

    private String readWikiUrl(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "wikiUrl");
        String wikiUrl = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "wikiUrl");
        return wikiUrl;
    }

    private int readImage(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "image");
        int image = Integer.parseInt(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, "image");
        return image;
    }

    private float readRating(XmlPullParser parser, String ratingName) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, ratingName);
        float rating = Float.parseFloat(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, ratingName);
        return rating;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }


}
