/* Copyright 2015 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/
package com.cyut.fruit;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.cyut.fruit.Classifier.Recognition;
import java.util.List;

public class RecognitionScoreView extends View implements ResultsView {
  private static final float TEXT_SIZE_DIP = 24;
  private List<Recognition> results;
  private final float textSizePx;
  private final Paint fgPaint;
  private final Paint bgPaint;;

  public RecognitionScoreView(final Context context, final AttributeSet set) {
    super(context, set);
    textSizePx =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
    fgPaint = new Paint();
    fgPaint.setTextSize(textSizePx);
    int fgcolor = Color.rgb(250 , 250,250);
    int bgcolor = Color.rgb(47,79,79);
    fgPaint.setColor(fgcolor);
    bgPaint = new Paint();
    bgPaint.setColor(bgcolor);
  }
  @Override
  public void setResults(final List<Recognition> results) {
    this.results = results;
    postInvalidate();
  }
  @Override
  public void onDraw(final Canvas canvas) {
    final int x = 30;
    int y = (int) (fgPaint.getTextSize() * 1.3f);
    if (results != null) {
      for (final Recognition recog : results) {
        canvas.drawPaint(bgPaint);
        //canvas.drawText(recog.getTitle() + ": " + recog.getConfidence(), x, y, fgPaint);
        //單一結果
        canvas.drawText(recog.getTitle(), x, y, fgPaint);
        y += fgPaint.getTextSize() * 1.5f;
      }
    }
  }
}
