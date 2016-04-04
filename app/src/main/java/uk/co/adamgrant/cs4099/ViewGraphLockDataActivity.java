package uk.co.adamgrant.cs4099;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.*;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;

/**
 * Activity which shows the Lock Data stored graphically
 */
public class ViewGraphLockDataActivity extends AppCompatActivity {
    GraphView graph;
    BarGraphSeries<DataPoint> series;
    private LockData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_lock_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialises the LockData from file.
        data = new LockData();
        // If data exists, initialise graph
        if(data.getEntries().size() != 0){
            Log.v("$$$$$$", "Initialising Lock Graph");

            initLockGraph();
        } else {
            Log.v("$$$$$$", "No Data Found");
        }
    }

    /**
     * Method which initialises the Lock Graph
     */
    public void initLockGraph(){
        GraphView graph = (GraphView) this.findViewById(R.id.lock_graph);

        // Initialises the data points using the lock data entries
        DataPoint points[] = new DataPoint[data.getNoEntries()*2];
        int count = 0;
        // Iterates through the data entries, creating data points
        for(LockDataEntry entry: data.getEntries()) {
            Date date = new Date(entry.getEpoch());
            points[count] = new DataPoint(date, (entry.getLocked() + 1) % 2);
            count++;
            points[count] = new DataPoint(date, entry.getLocked());
            count++;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
        graph.addSeries(series);

        // Set date label formatter, allowing points to be passed as epoch format
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3);
        graph.getGridLabelRenderer().setNumVerticalLabels(2);

        // Set manual axis bounds to ensure desired view
        graph.getViewport().setMinX(new Date(data.getEntries().get(0).getEpoch()).getTime());
        graph.getViewport().setMaxX(new Date(data.getEntries().get(data.getNoEntries() - 1).getEpoch()).getTime());
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(1);
        graph.getViewport().setYAxisBoundsManual(true);
    }

}
