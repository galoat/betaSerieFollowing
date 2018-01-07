package com.yaky.betaseriefollowing;


import static junit.framework.Assert.assertEquals;

import android.widget.Button;
import com.yaky.betaseriefollowing.ui.activities.LoginActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginScreenTest {

  private LoginActivity activity;

  @Before
  public void setUp() throws Exception
  {
    activity = Robolectric.buildActivity( LoginActivity.class )
        .create()
        .resume()
        .get();
  }


  @Test
  public void ValidateButtonShouldDisplayContentOfStringLoginOK() {

    Button validate  = (Button) activity.findViewById(R.id.validate);
    assertEquals(validate.getText().toString(), activity.getString(R.string.LoginOK));

  }
}
