package com.example.princ.inclass08;

/*Assignment# - InClass08
  Names : Sujanth Babu Guntupalli
          Mounika Yendluri
*/

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MainFragment.OnMainFragmentListener,DisplayFragment.OnDisplayFragmentListener,EditFragment.OnEditFragmentListener{

    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Student Registration");
        getSupportFragmentManager().beginTransaction().add(R.id.container,new MainFragment(),"main").commit();
    }

    @Override
    public void onMainFragmentInteraction(android.support.v4.app.Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment,"display").addToBackStack("null").commit();
    }

    @Override
    public void onDisplayFragmentInteraction(android.support.v4.app.Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment,"edit").addToBackStack("null").commit();
    }

    @Override
    public void onEditFragmentInteraction(android.support.v4.app.Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment,"display").addToBackStack("null").commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }
}
