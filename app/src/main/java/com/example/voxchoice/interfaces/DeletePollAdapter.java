package com.example.voxchoice.interfaces;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voxchoice.R;
import com.example.voxchoice.model.Poll;

import java.util.List;

public class DeletePollAdapter extends RecyclerView.Adapter<DeletePollAdapter.PollViewHolder> {
    private List<Poll> pollList;

    public DeletePollAdapter(List<Poll> pollList) {
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
