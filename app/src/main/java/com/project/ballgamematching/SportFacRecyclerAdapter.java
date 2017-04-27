package com.project.ballgamematching;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jylee on 2017-04-27.
 */

public class SportFacRecyclerAdapter extends RecyclerView.Adapter<SportFacRecyclerAdapter.ViewHolder>{

        // 각각 카드뷰에 들어갈 데이터. OpenAPI에서 받아와 적용하도록 할 것.
        private String[] titles = {"Capert One", "Chapter two", "Chapter three"};

        private String[] details = {"Item one","Item two","Item three"};

        private int[] images = { R.drawable.ic_basketball, R.drawable.ic_football, R.drawable.ic_badminton};

        private String[] placenm = {"풋살경기장","테니스장","축구장","족구장","야구장","배구장","다목적경기장","농구장","골프장","배드민턴장"};

        class ViewHolder extends RecyclerView.ViewHolder{
            public ImageView itemImage;
            public TextView itemTitle;
            public TextView itemDetail;

            public ViewHolder(View itemView) {
                super(itemView);
                itemImage = (ImageView) itemView.findViewById(R.id.iv_fac_list_img);
                itemTitle = (TextView) itemView.findViewById(R.id.tv_placenm);
                itemDetail = (TextView) itemView.findViewById(R.id.tv_sportnm);

                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();

                        Intent intent = new Intent(v.getContext(), SportFacInfoActivity.class);
                        v.getContext().startActivity(intent);
                    }
                });
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_sport_facility_item, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.itemTitle.setText(titles[position]);
            viewHolder.itemDetail.setText(details[position]);
            viewHolder.itemImage.setImageResource(images[position]);
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }

/*
    private class GetSportReservationDataTask extends AsyncTaskv<String, void, >{

    }
*/
}
