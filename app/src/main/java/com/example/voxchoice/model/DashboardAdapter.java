package com.example.voxchoice.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voxchoice.R;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.PollViewHolder> {

    private static Context context;
    private List<Poll> pollList;

    public DashboardAdapter(Context context, List<Poll> pollList) {
        this.context = context;
        this.pollList = pollList;
    }

    @NonNull
    @Override
    public PollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_items, parent, false);
        return new PollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PollViewHolder holder, int position) {
        Poll poll = pollList.get(position);
        holder.bind(poll);
    }

    @Override
    public int getItemCount() {
        return pollList.size();
    }

    public static class PollViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView questionTextView;
        private RecyclerView optionsRecyclerView;

        public PollViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.pollTitleTextView);
            optionsRecyclerView = itemView.findViewById(R.id.optionsRecyclerView);
        }

        public void bind(Poll poll) {
            titleTextView.setText(poll.getTitle());
            questionTextView.setText(poll.getQuestion());

            // Create and set adapter for options RecyclerView
        }
    }
}
