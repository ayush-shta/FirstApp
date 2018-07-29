package com.example.ayush.firstapp.mainList;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.ayush.firstapp.ListAdapter;
import com.example.ayush.firstapp.NavigationDrawerFragment;
import com.example.ayush.firstapp.R;
import com.example.ayush.firstapp.core.BaseActivity;
import java.util.List;

public class MainListActivity extends BaseActivity
    implements ListAdapter.OnItemClickListener, ListAdapter.OnItemLongClickListener,
    MainListContract.View {

  private RecyclerView recyclerView;
  private ListAdapter adapter;

  public static Intent getIntent(Context context) {
    return new Intent(context, MainListActivity.class);
  }

  @Override protected int getContextView() {
    return R.layout.main_list;
  }

  @Override protected int getToolbarResource() {
    return R.id.app_bar;
  }

  @Override protected void initializeActivity() {
    initRecycler();
    initAdapter();

    MainListContract.Presenter presenter = new MainListPresenter(this);
    presenter.setMainView(this);
    presenter.getItem();

    drawerFragment();
  }

  private void initAdapter() {
    adapter = new ListAdapter();
    adapter.setClickListener(this);
    adapter.setLongClickListener(this);
    recyclerView.setAdapter(adapter);
  }

  private void initRecycler() {
    recyclerView = findViewById(R.id.my_recycler);

    recyclerView.setHasFixedSize(true);

    RecyclerView.LayoutManager layoutManager =
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(layoutManager);

    recyclerView.addItemDecoration(new DividerItemDecoration(this,
        DividerItemDecoration.VERTICAL));
  }

  private void drawerFragment() {
    NavigationDrawerFragment drawerFragment =
        (NavigationDrawerFragment) getFragmentManager().findFragmentById(
            R.id.fragment_navigation_drawer);

    drawerFragment.setUp(R.id.fragment_navigation_drawer, findViewById(R.id.drawer_layout),
        getToolbar());
  }

  @Override public void populateList(List<String> list) {
    adapter.setDataSet(list);
  }

  @Override
  public void onItemClick(String data, int position) {
    switch (data) {
      case "Button Test":
        navigator.navigateButtonActivity(this);
        break;

      case "Image View Test":
        navigator.navigateImageActivity(this);
        break;

      case "Relative Layout Test":
        navigator.navigateRelativeLayoutActivity(this);
        break;

      case "Floating Button Test":
        navigator.navigateFloatingActionButtonActivity(this);
        break;

      case "Fragment Test":
        navigator.navigateFragmentTestActivity(this);
        break;

      case "Fragment Communication":
        navigator.navigateFragmentCommunicationActivity(this);
        break;

      case "Tabs":
        navigator.navigateTabsActivity(this);
        break;

      case "Passing Data Parcelable":
        navigator.navigatePassingDataActivity(this);
        break;

      default:
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onItemLongClick(String data, int position) {
    //Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    Snackbar.make(findViewById(android.R.id.content), data, Snackbar.LENGTH_SHORT)
        .show();
  }

  /**
   * toolbar menu implementation
   **/
  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.settings:
        Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}