package comc.example.mohammedmorse.baker.IdlingTest;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;
/**
 * Created by Mohammed Morse on 18/09/2018.
 */

public class SimpleIdlingResources implements IdlingResource{
  @NonNull public ResourceCallback callback;
 public volatile AtomicBoolean aBoolean= new AtomicBoolean(true);;
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return aBoolean.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
           this.callback=callback;
    }
     public void setIdleState(boolean iDlingState){
        aBoolean.set(iDlingState);
        if(iDlingState && callback!=null){
            callback.onTransitionToIdle();
        }
     }
}
