package bhouse.myapplication;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;
import android.view.SurfaceHolder;

/**
 * Created by megha on 15-05-10.
 */
public class WatchFaceService extends CanvasWatchFaceService {

  @Override
  public Engine onCreateEngine() {
    return new Engine();
  }

  private class Engine extends CanvasWatchFaceService.Engine {

    private CustomWatchFace watchFace;

    @Override
    public void onCreate(SurfaceHolder holder) {
      super.onCreate(holder);

      setWatchFaceStyle(new WatchFaceStyle.Builder(WatchFaceService.this)
        .setCardPeekMode(WatchFaceStyle.PEEK_MODE_SHORT)
        .setAmbientPeekMode(WatchFaceStyle.AMBIENT_PEEK_MODE_HIDDEN)
        .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
        .setShowSystemUiTime(false)
        .build());

      watchFace = CustomWatchFace.newInstance(WatchFaceService.this);
    }

    @Override
    public void onTimeTick() {
      super.onTimeTick();
      invalidate();
    }

    @Override
    public void onDraw(Canvas canvas, Rect bounds) {
      watchFace.draw(canvas, bounds);
    }

  }

}
