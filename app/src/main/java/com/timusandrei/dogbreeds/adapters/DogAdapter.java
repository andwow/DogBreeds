package com.timusandrei.dogbreeds.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timusandrei.dogbreeds.R;
import com.timusandrei.dogbreeds.interfaces.OnDogItemClick;
import com.timusandrei.dogbreeds.models.Dog;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Dog> dogs;

    private final OnDogItemClick onDogItemClick;

    public DogAdapter(List<Dog> dogs, OnDogItemClick onDogItemClick) {
        this.dogs = dogs;
        this.onDogItemClick = onDogItemClick;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dog_cell, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        Dog dog = (Dog) dogs.get(position);
        ((DogViewHolder) holder).bind(dog);
    }

    @Override
    public int getItemCount() {
        return dogs.size();
    }

    class DogViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView description;
        private final ImageView profileImage;
        private final View view;

        DogViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.dog_name);
            description = view.findViewById(R.id.dog_description);
            profileImage = view.findViewById(R.id.dog_profile_image);
            this.view = view;
        }

        void bind(Dog dog) {
            name.setText(dog.getName());
            description.setText(dog.getShortDescription());
            profileImage.setImageResource(dog.getProfileImage());

            view.setOnClickListener(v -> onDogItemClick.onClick(dog));
        }
    }
}
