package com.example.voxchoice.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voxchoice.R;
import com.example.voxchoice.VoteActivity;

import java.util.List;

public class ViewPollAdapter extends RecyclerView.Adapter<ViewPollAdapter.PollViewHolder> {
    private List<Poll> pollList;
    private OnPollClickListener onPollClickListener; // Declare the listener

    public ViewPollAdapter(List<Poll> pollList, OnPollClickListener onPollClickListener) {
        this.pollList = pollList;
        this.onPollClickListener = onPollClickListener; // Initialize the listener
    }

    // Define the custom interface
    public interface OnPollClickListener {
        void onPollClick(int position);
    }

    @NonNull
    @Override
    public ViewPollAdapter.PollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poll_item, parent, false);
        return new ViewPollAdapter.PollViewHolder(view, onPollClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPollAdapter.PollViewHolder holder, int position) {
        Poll poll = pollList.get(position);
        holder.bind(poll);
    }

    @Override
    public int getItemCount() {
        return pollList.size();
    }

    public static class PollViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button titleButton;
//        private List<Poll> pollList;
        private Context context;
        private OnPollClickListener onPollClickListener;

        public PollViewHolder(@NonNull View itemView, OnPollClickListener onPollClickListener) {
            super(itemView);
//            this.pollList = pollList;
            this.onPollClickListener = onPollClickListener;
            titleButton = itemView.findViewById(R.id.titleButton);
            context = itemView.getContext();
            titleButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && onPollClickListener != null) {
                onPollClickListener.onPollClick(position);
            }
        }

        public void bind(Poll poll) {
            titleButton.setText(poll.getTitle());
        }
    }
}
