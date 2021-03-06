package com.example.taskaway;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Adapter for ViewOtherBids class's Recyclerview. Will display bids, bidder name, bidder amount, images,
 * and the selection button.
 *
 * @author Created by Jonathan Ismail, Edited by Katherine Mae Patenio
 * Created on 2018-04-01.
 *
 * @see ViewOwnTask
 * @see ViewOtherBids
 */
public class OtherBidsViewAdapter extends RecyclerView.Adapter<OtherBidsViewAdapter.MyViewHolder>{
    private Context mContext;
    private ArrayList<Bid> mData;
    private int lastPosition = -1;
    private OnBidClickListener onBidClickListener;


    /**
     * Adapter for ViewOtherBids
     *
     * @param mContext - the context of the adapter
     * @param mData - the list of bids for the current task
     */
    public OtherBidsViewAdapter(Context mContext, ArrayList<Bid> mData, OnBidClickListener onBidClickListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.onBidClickListener = onBidClickListener;
    }


    /**
     * Creates and initializes the view holder of the adapter.
     *
     * @param parent - Viewgroup that holds the view holder and possibly other children views
     * @param viewType - the type of view to be used
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_otherbids,parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;

    }

    /**
     * Determines what will be Displayed on the ViewHolder. Will specifically display the bid
     * information: bidder name, bidder amount.
     *
     * @param holder - instance of ViewHolder
     * @param position - current position of the task selected/displayed
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        /* READ BIDS FROM CURRENT TASK AND DISPLAY THEM */
        String userid;
        User user;
        int userindex;
        if (MainActivity.isOnline()){ // online
            user = ServerWrapper.getUserFromId(mData.get(position).getUserId());
        }else{ // offline
            userid = mData.get(position).getUserId();
            SaveFileController saveFileController = new SaveFileController();
            user = saveFileController.getUserFromUserId(this.mContext, userid);
            userindex = saveFileController.getUserIndexFromCreatorID(mContext, userid);
        }

        /* USERNAME DISPLAY */
        String username = user.getUsername();
        holder.tv_name.setText(username);

        /* BID AMOUNT DISPLAY */
        float amount;
        String strAmount;

        // SOURCE: http://www.zoftino.com/android-recyclerview-radiobutton
        amount = mData.get(position).getAmount();
        strAmount = String.format("$%.2f", amount);
        holder.tv_otherbid.setText(strAmount);

        // If one button clicked, unset another one if already clicked
        holder.radioButton.setChecked(lastPosition == position);

        }

    /**
     * Returns the size of mData or the TaskList containing bids
     *
     * @return size of mData as an Integer
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }


    /**
     * Sets the layouts to be set to the current adapter holder. Also determines radio button
     * behaviour.
     *
     * tv_name = container that holds the bidder's name
     * tv_otherbid = container that holds the bidder's amount
     *
     * @author Created by Jonathan Ismail, Edited by Katherine Mae Patenio
     *
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_otherbid;
        private RadioButton radioButton;

        public MyViewHolder(final View itemView) {

            // SOURCE: https://stackoverflow.com/a/47183251
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.name_other);
            tv_otherbid = (TextView) itemView.findViewById(R.id.bid_other);

            // SOURCE: http://www.zoftino.com/android-recyclerview-radiobutton
            radioButton = (RadioButton) itemView.findViewById(R.id.img_status);
            radioButton.setOnClickListener(new View.OnClickListener() {
                /**
                 * When a radio button is clicked, pass information on which item was selected to
                 * ViewOtherBids activity. This will simply be used for the Decline and Accept buttons.
                 *
                 * @param v - instance of View
                 *
                 * @see ViewOtherBids
                 * @see OnBidClickListener
                 */
                @Override
                public void onClick(View v) {
                    lastPosition = getAdapterPosition();
                    notifyDataSetChanged();
                    final Bid bid = mData.get(lastPosition);
                    onBidClickListener.onBidClick(bid,lastPosition);
                }
            });
        }
    }
}