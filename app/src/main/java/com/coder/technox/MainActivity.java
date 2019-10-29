package com.coder.technox;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static com.coder.technox.R.string.navigation_drawer_open;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private  static final String companyNumber="01022342098";

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static String FACEBOOK_URL = "https://www.facebook.com/technoxmarketing";
    public static String FACEBOOK_PAGE_ID = "2043434129287989";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        setContentView(R.drawable.fab_add);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button androidActvityBut = findViewById(R.id.android_but);
        //intent to open android activity
        androidActvityBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, AndroidActivity.class));
            }
        });
      //  Log.d("MainActivity", "Hello body ");
        Button tweFab = findViewById(R.id.fab_tweeter);
        tweFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //intent to open tweeter app and checks if tweeter app is installed
                Intent twitterIntent = getOpenTwitterIntent(MainActivity.this);
                if (twitterIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(twitterIntent);}
               // Log.e("TAG", String.valueOf(twitterIntent));
            }
        });
        Button marktingActvityBut = findViewById(R.id.markting_but);
        //intent to open marktingActvity

        marktingActvityBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, MarktingActivity.class));}

        });

        final Button fabCall = findViewById(R.id.fabCall);
        final Button fab_face = findViewById(R.id.fab_face);
        fab_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage(FACEBOOK_URL);

//                startActivity(new Intent(Intent.ACTION_VIEW,
//                        Uri.parse(FACEBOOK_URL)));
      /*  Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(MainActivity.this);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);*/
            }

        });
        Button inistagramImg = findViewById(R.id.fab_instagram);
        inistagramImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uri obeject to parce technox instagram page
                Uri uri = Uri.parse("https://www.instagram.com/technox33");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                //intent to open tecnox page on instagram app and checks if instagram app is installed
                likeIng.setPackage("instagram");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/technox33")));
                }
            }
        });
        Button linkedIn_fab = findViewById(R.id.fab_Linked);
        linkedIn_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.linkedin.com/in/techno-x-marketing-agency-164a64188");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                //intent to open tecnox page on linkedin app and checks if linkedin app is installed

                likeIng.setPackage("com.linkedin.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.linkedin.com/in/techno-x-marketing-agency-164a64188")));
                }
            }
        });

        Button mailFab;
        mailFab = findViewById(R.id.fab_Mail);
        mailFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent  to open mail app
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"technoxmarketing@gmail.com"});  //developer 's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "Dear ," + "");  //Email 's Greeting text
                if (Email.resolveActivity(getPackageManager()) != null) {

                    startActivity(Intent.createChooser(Email, "Send Feedback:"));
            }}
        });
        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //intent to make a call with company number
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + companyNumber)));
                  //Snackbar.make(view, "call: 01001002576", Snackbar.LENGTH_LONG)
                //        .setAction("call", null).show();

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }
    //dialPhoneNumber function to call the company
    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    //getOpenTwitterIntent to check if twitter app is installed
    public static Intent getOpenTwitterIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.twitter.android", 0); //Checks if Twitter is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/TechnoAgency")); //Trys to make intent with Twitter's's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/TechnoAgency")); //catches and opens a url to the desired page
        }
    }
    // openWebPage to open url page and checking if the user has the an app to open url pages
    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


   /* public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }*/
    //method to get the right URL to use in the intent
    /* public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url

        }
    }
*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about_menu_item) {
            startActivity(new Intent(MainActivity.this, MarktingActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.designs) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.behance.net/portfolio/editor?project_id=77846947")));
        } else if (id == R.id.nav_gallery) {

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/pg/TechnoXMarketing/photos/?ref=page_internal")));

        }

         else if (id == R.id.moderation) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/2043434129287989/posts/2110437675920967")));
         }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
