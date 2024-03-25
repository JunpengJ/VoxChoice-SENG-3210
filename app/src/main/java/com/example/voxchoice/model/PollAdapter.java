package com.example.voxchoice.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voxchoice.R;

import java.util.List;

public class PollAdapter extends RecyclerView.Adapter<PollAdapter.PollViewHolder> {
    private List<Poll> pollList;

    public PollAdapter(List<Poll> pollList) {
        this.pollList = pollList;
    }

    @NonNull
    @Override
    public PollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poll_item, parent, false);
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
        private Button titleButton;

        public PollViewHolder(@NonNull View itemView) {
            super(itemView);
            titleButton = itemView.findViewById(R.id.titleButton);
        }

        public void bind(Poll poll) {
            titleButton.setText(poll.getTitle());
        }
    }
}
