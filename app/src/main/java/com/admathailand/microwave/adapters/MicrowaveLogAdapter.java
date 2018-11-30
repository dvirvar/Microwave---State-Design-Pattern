package com.admathailand.microwave.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.admathailand.microwave.R;
import com.admathailand.microwave.objects.MicrowaveLog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MicrowaveLogAdapter extends RecyclerView.Adapter<MicrowaveLogAdapter.MicrowaveLogViewHolder> {
    private Context context;
    private RecyclerView rvLog;
    private List<MicrowaveLog> microwaveLogList;

    public MicrowaveLogAdapter(Context context,RecyclerView rvLog) {
        this.context = context;
        this.rvLog = rvLog;
        microwaveLogList = new LinkedList<>();
    }

    @NonNull
    @Override
    public MicrowaveLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.microwave_log_item,parent,false);
        return new MicrowaveLogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MicrowaveLogViewHolder holder, int position) {
        final MicrowaveLog microwaveLog = microwaveLogList.get(holder.getAdapterPosition());
        holder.tvTime.setText(microwaveLog.getTime());
        holder.tvDescription.setText(microwaveLog.getDescription());
    }

    @Override
    public int getItemCount() {
        return microwaveLogList.size();
    }

    public void addLog(MicrowaveLog log){
        microwaveLogList.add(0,log);
        notifyItemInserted(0);
        rvLog.scrollToPosition(0);
    }

    static class MicrowaveLogViewHolder extends RecyclerView.ViewHolder{
        TextView tvTime,tvDescription;
        MicrowaveLogViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
