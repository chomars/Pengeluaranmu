package apps.chomars.pengeluaranmu;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MenuActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	
	private ListView mDrawerList;

	private ActionBarDrawerToggle mDrawerToggle;

	private String mTitle = "";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menu);

//		mTitle = "Daftar Pengeluaran";
		mTitle = "Rekap";
		getActionBar().setTitle(mTitle);
		DisplayRekapActivity fragment = new DisplayRekapActivity();
		FragmentManager fragmentManager = getFragmentManager();

		FragmentTransaction ft = fragmentManager.beginTransaction();

		ft.replace(R.id.content_frame, fragment);

		ft.commit();

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,

		R.drawable.ic_drawer, R.string.drawer_open,

		R.string.drawer_close) {

			/** Called when drawer is closed */

			public void onDrawerClosed(View view) {

				getActionBar().setTitle(mTitle);

				invalidateOptionsMenu();

			}

			/** Called when a drawer is opened */

			public void onDrawerOpened(View drawerView) {

				getActionBar().setTitle("Manajemen Keuangan");

				invalidateOptionsMenu();

			}

		};

		// Setting DrawerToggle on DrawerLayout

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// Creating an ArrayAdapter to add items to the listview mDrawerList

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(),

				R.layout.drawer_list_item, getResources().getStringArray(
						R.array.menus));

		// Setting the adapter on mDrawerList

		mDrawerList.setAdapter(adapter);

		// Enabling Home button

		getActionBar().setHomeButtonEnabled(true);

		// Enabling Up navigation

		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Setting item click listener for the listview mDrawerList

		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,

			int position, long id) {

				// Getting an array of rivers

				String[] menuItems = getResources().getStringArray(
						R.array.menus);

				// Currently selected river

				mTitle = menuItems[position];
				Log.d(mTitle, mTitle);

				if (mTitle.equals("Rekap")) {
					DisplayRekapActivity fragmentone = new DisplayRekapActivity();
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction ft = fragmentManager.beginTransaction();

					ft.replace(R.id.content_frame, fragmentone);
					ft.commit();
				} else if (mTitle.equals("Input Pengeluaran")) {
					FormPengeluaranActivity fragment = new FormPengeluaranActivity();
					FragmentManager fragmentManager = getFragmentManager();
					// Creating a fragment transaction
					FragmentTransaction ft = fragmentManager.beginTransaction();
					// Adding a fragment to the fragment transaction
					ft.replace(R.id.content_frame, fragment);

					// Committing the transaction

					ft.commit();
				} else if (mTitle.equals("Daftar Pengeluaran")) {
					DisplayPengeluaranActivity fragment = new DisplayPengeluaranActivity();
					FragmentManager fragmentManager = getFragmentManager();
					// Creating a fragment transaction
					FragmentTransaction ft = fragmentManager.beginTransaction();
					// Adding a fragment to the fragment transaction
					ft.replace(R.id.content_frame, fragment);
					// Committing the transaction

					ft.commit();

				} else if (mTitle.equals("Daftar Pemasukan")) {
					DisplayPemasukanActivity fragment = new DisplayPemasukanActivity();
					FragmentManager fragmentManager = getFragmentManager();
					// Creating a fragment transaction
					FragmentTransaction ft = fragmentManager.beginTransaction();
					// Adding a fragment to the fragment transaction
					ft.replace(R.id.content_frame, fragment);
					// Committing the transaction

					ft.commit();

				} else if (mTitle.equals("Register")) {
					RegisterActivity fragment = new RegisterActivity();
					FragmentManager fragmentManager = getFragmentManager();
					// Creating a fragment transaction
					FragmentTransaction ft = fragmentManager.beginTransaction();
					// Adding a fragment to the fragment transaction
					ft.replace(R.id.content_frame, fragment);
					// Committing the transaction

					ft.commit();

				}
				else if (mTitle.equals("Input Pemasukan")) {
					FormPemasukanActivity fragment = new FormPemasukanActivity();
					FragmentManager fragmentManager = getFragmentManager();
					// Creating a fragment transaction
					FragmentTransaction ft = fragmentManager.beginTransaction();
					// Adding a fragment to the fragment transaction
					ft.replace(R.id.content_frame, fragment);
					// Committing the transaction

					ft.commit();
				}
				else if (mTitle.equals("Export Data")) {
					ExportData fragment = new ExportData();
					FragmentManager fragmentManager = getFragmentManager();
					// Creating a fragment transaction
					FragmentTransaction ft = fragmentManager.beginTransaction();
					// Adding a fragment to the fragment transaction
					ft.replace(R.id.content_frame, fragment);
					// Committing the transaction

					ft.commit();

				}
				
					else {

				}

				// Closing the drawer

				mDrawerLayout.closeDrawer(mDrawerList);

			}

		});

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {

		super.onPostCreate(savedInstanceState);

		mDrawerToggle.syncState();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (mDrawerToggle.onOptionsItemSelected(item)) {

			return true;

		}

		return super.onOptionsItemSelected(item);

	}

	/** Called whenever we call invalidateOptionsMenu() */

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		// If the drawer is open, hide action items related to the content view

		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);

		return super.onPrepareOptionsMenu(menu);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);

		return true;

	}
	 /**
     * This class makes the ad request and loads the ad.
     */
    public static class AdFragment extends Fragment {

        private AdView mAdView;

        public AdFragment() {
        }

        @Override
        public void onActivityCreated(Bundle bundle) {
            super.onActivityCreated(bundle);

            // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
            // values/strings.xml.
            mAdView = (AdView) getView().findViewById(R.id.adView);

            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
//            AdRequest adRequest = new AdRequest.Builder()
//                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                    .build();
            AdRequest adRequest = new AdRequest.Builder()
          .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
          .build();
            // Start loading the ad in the background.
            mAdView.loadAd(adRequest);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_ad, container, false);
        }

        /** Called when leaving the activity */
        @Override
        public void onPause() {
            if (mAdView != null) {
                mAdView.pause();
            }
            super.onPause();
        }

        /** Called when returning to the activity */
        @Override
        public void onResume() {
            super.onResume();
            if (mAdView != null) {
                mAdView.resume();
            }
        }

        /** Called before the activity is destroyed */
        @Override
        public void onDestroy() {
            if (mAdView != null) {
                mAdView.destroy();
            }
            super.onDestroy();
        }

    }
}

