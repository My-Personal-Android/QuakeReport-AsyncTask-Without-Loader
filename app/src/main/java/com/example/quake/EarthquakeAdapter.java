package com.example.quake;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<EarthQuake> {

    public EarthquakeAdapter(@NonNull Activity context, List<EarthQuake> earthQuakeList) {
        super(context, 0,earthQuakeList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= convertView;
        if (view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.fortextviews,parent,false);
        }

        EarthQuake currentearthQuake = getItem(position);

        TextView magnitude=(TextView) view.findViewById(R.id.magnitude);

        String mag=formatMagnitude(currentearthQuake.getMagnitude());
        magnitude.setText(mag);


        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentearthQuake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        String originalName=currentearthQuake.getName();

        if(originalName.contains("of")) {

            String[] array = originalName.split(" of ");

            TextView offsetname = (TextView) view.findViewById(R.id.offsetname);

            offsetname.setText(array[0].toString()+" of ");


            TextView primaryname = (TextView) view.findViewById(R.id.primaryname);

            primaryname.setText(array[1].toString());
        }
    else{
            TextView offsetname = (TextView) view.findViewById(R.id.offsetname);

            offsetname.setText(R.string.Near_the);

            TextView primaryname = (TextView) view.findViewById(R.id.primaryname);

            primaryname.setText(originalName);
        }


        Date dateObject = new Date(currentearthQuake.getTimeinmillisecond());

        // Find the TextView with view ID date
        TextView dateView = (TextView) view.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);


        TextView timeView = (TextView) view.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);




        return view;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
// both function are same but format is different
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
