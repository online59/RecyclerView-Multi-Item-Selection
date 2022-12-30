package com.example.recyclerviewmutiselection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final List<Category> categoryList;
    private SelectionTracker<Long> tracker;

    public CategoryAdapter() {
        this.categoryList = createCategoryList();
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        if (position != RecyclerView.NO_POSITION) {
            holder.getTvCategoryName().setText(categoryList.get(position).getCategory());
            holder.itemView.setActivated(tracker.isSelected((long) position));
        }
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public void setTracker(SelectionTracker<Long> tracker) {
        this.tracker = tracker;
    }

    // Set each item in recyclerview to use its position as their id
    @Override
    public long getItemId(int position) {
        return (long) position;
    }


    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvCategoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCategoryName = itemView.findViewById(R.id.tv_category);
        }

        public TextView getTvCategoryName() {
            return tvCategoryName;
        }


        // Provide item details for ItemDetailsLookup class
        public ItemDetailsLookup.ItemDetails<Long> getItemDetails() {
            return new ItemDetailsLookup.ItemDetails<Long>() {
                @Override
                public int getPosition() {
                    return getAdapterPosition();
                }

                @Nullable
                @Override
                public Long getSelectionKey() {
                    return getItemId();
                }
            };
        }
    }

    public static class Category {
        private String category;

        public Category() {
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

    public List<Category> createCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        Category category;

        String[] expenseCategoryList = {"Transportation", "Accommodation", "Lunch", "Breakfast", "Dinner",
                "Desserts", "Shopping", "Fuel", "Bills", "Education", "Kids", "Pets", "Saving", "Healthcare",
                "Drinks", "Rents", "Troll Way", "Travel", "Clothing", "Utilities", "Groceries", "iTunes",
                "Youtube Music", "Netflix", "Disney+ Hotstar", "Other"};

        for (String s : expenseCategoryList) {
            category = new Category();
            category.setCategory(s);
            categoryList.add(category);
        }

        return categoryList;
    }
}
