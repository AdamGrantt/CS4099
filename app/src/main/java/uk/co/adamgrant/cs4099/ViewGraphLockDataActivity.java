package uk.co.adamgrant.cs4099;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.androidplot.Plot;
import com.androidplot.xy.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class ViewGraphLockDataActivity extends Activity {

    private XYPlot mySimpleXYPlot;
    private SimpleXYSeries[] series = null;
    private PointF minXY;
    private PointF maxXY;

    private LockData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_lock_data);
        data = new LockData();
        if(data.getData()!=null){
            initLockGraph();
        } else {
            // NO DATA FOUND MESSAGE
        }
    }

    public void initLockGraph(){
        mySimpleXYPlot = (XYPlot) findViewById(R.id.lock_graph);
//        mySimpleXYPlot.setOnTouchListener(this);
        mySimpleXYPlot.getGraphWidget().setTicksPerRangeLabel(2);
        mySimpleXYPlot.getGraphWidget().setTicksPerDomainLabel(2);
        mySimpleXYPlot.getGraphWidget().getBackgroundPaint().setColor(Color.TRANSPARENT);
        mySimpleXYPlot.getGraphWidget().setRangeValueFormat(
                new DecimalFormat("#####"));
        mySimpleXYPlot.getGraphWidget().setDomainValueFormat(
                new DecimalFormat("#####.#"));
        mySimpleXYPlot.getGraphWidget().setRangeTickLabelWidth(25);
        mySimpleXYPlot.setRangeLabel("");
        mySimpleXYPlot.setDomainLabel("");

        mySimpleXYPlot.setBorderStyle(Plot.BorderStyle.NONE, null, null);
        //mySimpleXYPlot.disableAllMarkup();
        series = new SimpleXYSeries[1];
        series[0] = new SimpleXYSeries("S");
        populateSeries(series[0]);


        mySimpleXYPlot.addSeries(series[0],
                new LineAndPointFormatter(Color.rgb(0, 0, 0), null,
                        Color.rgb(0, 0, 150), null));

        mySimpleXYPlot.redraw();
        mySimpleXYPlot.calculateMinMaxVals();
        minXY = new PointF(mySimpleXYPlot.getCalculatedMinX().floatValue(),
                mySimpleXYPlot.getCalculatedMinY().floatValue());
        maxXY = new PointF(mySimpleXYPlot.getCalculatedMaxX().floatValue(),
                mySimpleXYPlot.getCalculatedMaxY().floatValue());
    }

    private void populateSeries(SimpleXYSeries series) {
        for(LockDataEntry entry : data.getData()) {
            series.addLast(entry.getEpoch(), entry.getLocked());
        }
    }

    // Definition of the touch states
    static final int NONE = 0;
    static final int ONE_FINGER_DRAG = 1;
    static final int TWO_FINGERS_DRAG = 2;
    int mode = NONE;

    PointF firstFinger;
    float distBetweenFingers;
    boolean stopThread = false;

//    @Override
//    public boolean onTouch(View arg0, MotionEvent event) {
//        switch (event.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_DOWN: // Start gesture
//                firstFinger = new PointF(event.getX(), event.getY());
//                mode = ONE_FINGER_DRAG;
//                stopThread = true;
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_POINTER_UP:
//                mode = NONE;
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN: // second finger
//                distBetweenFingers = spacing(event);
//                // the distance check is done to avoid false alarms
//                if (distBetweenFingers > 5f) {
//                    mode = TWO_FINGERS_DRAG;
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (mode == ONE_FINGER_DRAG) {
//                    PointF oldFirstFinger = firstFinger;
//                    firstFinger = new PointF(event.getX(), event.getY());
//                    scroll(oldFirstFinger.x - firstFinger.x);
//                    mySimpleXYPlot.setDomainBoundaries(minXY.x, maxXY.x,
//                            BoundaryMode.FIXED);
//                    mySimpleXYPlot.redraw();
//
//                } else if (mode == TWO_FINGERS_DRAG) {
//                    float oldDist = distBetweenFingers;
//                    distBetweenFingers = spacing(event);
//                    zoom(oldDist / distBetweenFingers);
//                    mySimpleXYPlot.setDomainBoundaries(minXY.x, maxXY.x,
//                            BoundaryMode.FIXED);
//                    mySimpleXYPlot.redraw();
//                }
//                break;
//        }
//        return true;
//    }

//    private void zoom(float scale) {
//        float domainSpan = maxXY.x - minXY.x;
//        float domainMidPoint = maxXY.x - domainSpan / 2.0f;
//        float offset = domainSpan * scale / 2.0f;
//
//        minXY.x = domainMidPoint - offset;
//        maxXY.x = domainMidPoint + offset;
//
//        minXY.x = Math.min(minXY.x, series[0].getX(series[0].size() - 3)
//                .floatValue());
//        maxXY.x = Math.max(maxXY.x, series[0].getX(1).floatValue());
//        clampToDomainBounds(domainSpan);
//    }
//
//    private void scroll(float pan) {
//        float domainSpan = maxXY.x - minXY.x;
//        float step = domainSpan / mySimpleXYPlot.getWidth();
//        float offset = pan * step;
//        minXY.x = minXY.x + offset;
//        maxXY.x = maxXY.x + offset;
//        clampToDomainBounds(domainSpan);
//    }

//    private void clampToDomainBounds(float domainSpan) {
//        float leftBoundary = series[0].getX(0).floatValue();
//        float rightBoundary = series[0].getX(series[0].size() - 1).floatValue();
//        // enforce left scroll boundary:
//        if (minXY.x < leftBoundary) {
//            minXY.x = leftBoundary;
//            maxXY.x = leftBoundary + domainSpan;
//        } else if (maxXY.x > series[0].getX(series[0].size() - 1).floatValue()) {
//            maxXY.x = rightBoundary;
//            minXY.x = rightBoundary - domainSpan;
//        }
//    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.hypot(x, y);
    }

}
