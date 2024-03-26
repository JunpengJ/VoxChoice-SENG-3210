package com.example.voxchoice.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voxchoice.R;
import com.example.voxchoice.model.Poll;

import java.util.List;

public class DashboardAdapter {

    public static class ViewPollAdapter extends RecyclerView.Adapter<ViewPollAdapter.PollViewHolder> {
        private Context context;
        private List<Poll> pollList;

        public ViewPollAdapter(Context context, List<Poll> pollList) {
            this.context = context;
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
            private TextView textViewPollTitle;
            private LinearLayout linearLayoutOptions;

            public PollViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewPollTitle = itemView.findViewById(R.id.dashboardPollTitle);
                linearLayoutOptions = itemView.findViewById(R.id.optionsRecyclerView);
            }

            public void bind(Poll poll) {
                textViewPollTitle.setText(poll.getTitle());
                linearLayoutOptions.removeAllViews(); // Clear existing views

                // Add TextViews for options and vote counts dynamically
                for (int i = 0; i < poll.getOptions().size(); i++) {
                    String option = poll.getOptions().get(i);
                    int votes = poll.getVotes().get(i);

                    // Create TextView for option
                    TextView textViewOption = new TextView(itemView.getContext());
                    textViewOption.setText(option);

                    // Create TextView for vote count
                    TextView textViewVoteCount = new TextView(itemView.getContext());
                    textViewVoteCount.setText("Votes: " + votes);

                    // Add TextViews to LinearLayout
                    linearLayoutOptions.addView(textViewOption);
                    linearLayoutOptions.addView(textViewVoteCount);
                }
            }
        }
    }
}
