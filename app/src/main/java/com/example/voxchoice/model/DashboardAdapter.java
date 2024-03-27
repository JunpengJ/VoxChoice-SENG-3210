package com.example.voxchoice.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voxchoice.R;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.OptionViewHolder> {

    private List<String> options;
    private List<Integer> votes;


    public DashboardAdapter(List<String> options,List<Integer> votes) {
        this.options = options;
        this.votes = votes;
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_items, parent, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        String option = options.get(position);
        int voteCount = votes.get(position);
        holder.bind(option, voteCount);
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public static class OptionViewHolder extends RecyclerView.ViewHolder {
        private TextView optionTextView;
        private TextView voteCountTextView;



        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            optionTextView = itemView.findViewById(R.id.optionTextView);
            voteCountTextView = itemView.findViewById(R.id.voteCountTextView);
        }

        public void bind(String option, int voteCount) {
            optionTextView.setText(option);
            voteCountTextView.setText("Votes: " + voteCount); // Make sure voteCount is an integer representing the vote count
        }
    }
}
