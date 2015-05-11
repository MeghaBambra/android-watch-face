package bhouse.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.format.Time;

/**
 * Created by megha on 15-05-11.
 */
public class CustomWatchFace {

  private static final String TIME_FORMAT = "%02d:%02d";
  private static final String DATE_FORMAT = "%02d.%02d.%d";

  private final Paint timePaint;
  private final Paint datePaint;

  private final int backgroundColor;

  CustomWatchFace(Paint timePaint, Paint datePaint, int backgroundColor) {
    this.timePaint = timePaint;
    this.datePaint = datePaint;
    this.backgroundColor = backgroundColor;
  }

  public static CustomWatchFace newInstance(Context context) {
    Resources resources = context.getResources();

    Paint timePaint = new Paint();
    timePaint.setColor(resources.getColor(R.color.watch_face_time));
    timePaint.setTextSize(resources.getDimension(R.dimen.time_size));
    timePaint.setAntiAlias(true);

    Paint datePaint = new Paint();
    datePaint.setColor(resources.getColor(R.color.watch_face_date));
    datePaint.setTextSize(context.getResources().getDimension(R.dimen.date_size));
    datePaint.setAntiAlias(true);

    return new CustomWatchFace(timePaint, datePaint, resources.getColor(R.color.watch_face_fill));
  }

  public void draw(Canvas canvas, Rect bounds) {

    Time time = new Time();
    time.setToNow();

    canvas.drawColor(backgroundColor);

    float centerX = bounds.exactCenterX();
    float centerY = bounds.exactCenterY();
    Rect boundingBox = new Rect();

    String timeText = String.format(TIME_FORMAT, time.hour, time.minute);
    float timeCenterX = centerX - (timePaint.measureText(timeText) / 2.0f);
    timePaint.getTextBounds(timeText, 0, timeText.length(), boundingBox);
    float timeCenterY = centerY + (boundingBox.height() / 2.0f);
    canvas.drawText(timeText, timeCenterX, timeCenterY, timePaint);

    String dateText = String.format(DATE_FORMAT, time.monthDay, time.month + 1, time.year);
    float dateCenterX = centerX - (datePaint.measureText(dateText) / 2.0f);
    float dateCenterY = timeCenterY - (boundingBox.height() + 10.0f);
    canvas.drawText(dateText, dateCenterX, dateCenterY, datePaint);
  }

}
