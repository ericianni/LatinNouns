package fishpondproductions.latinnouns;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LandingPageActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return LandingPageFragment.newInstance();
    }

    @Override
    public void onBackPressed() {
        int count = this.getSupportFragmentManager().getBackStackEntryCount();
        Log.d("Landingact: ", "Count: " + count);
        Fragment customQuizFragment = this.getSupportFragmentManager().findFragmentByTag(LandingPageFragment.CUSTOM_QUIZ_TAG);
        Fragment declineNounFragment = this.getSupportFragmentManager().findFragmentByTag(LandingPageFragment.DECLINE_NOUN_TAG);
        if (declineNounFragment != null) {
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

    }
}
