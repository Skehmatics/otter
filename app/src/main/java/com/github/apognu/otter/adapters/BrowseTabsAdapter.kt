package com.github.apognu.otter.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.github.apognu.otter.R
import com.github.apognu.otter.fragments.*
import com.github.apognu.otter.utils.Settings

class BrowseTabsAdapter(val context: Fragment, manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
  var tabs = mutableListOf<Fragment>()

  override fun getCount(): Int {
    return if (Settings.areExperimentsEnabled()) 5 else 4
  }

  override fun getItem(position: Int): Fragment {
    tabs.getOrNull(position)?.let {
      return it
    }

    val fragment =
      if (Settings.areExperimentsEnabled()) {
        when (position) {
          0 -> ArtistsFragment()
          1 -> AlbumsGridFragment()
          2 -> PlaylistsFragment()
          3 -> RadiosFragment()
          4 -> FavoritesFragment()
          else -> ArtistsFragment()
        }
      } else {
        when (position) {
          0 -> ArtistsFragment()
          1 -> AlbumsGridFragment()
          2 -> PlaylistsFragment()
          3 -> FavoritesFragment()
          else -> ArtistsFragment()
        }
      }

    tabs.add(position, fragment)

    return fragment
  }

  override fun getPageTitle(position: Int): String {
    return if (Settings.areExperimentsEnabled()) {
      when (position) {
        0 -> context.getString(R.string.artists)
        1 -> context.getString(R.string.albums)
        2 -> context.getString(R.string.playlists)
        3 -> context.getString(R.string.radios)
        4 -> context.getString(R.string.favorites)
        else -> ""
      }
    } else {
      when (position) {
        0 -> context.getString(R.string.artists)
        1 -> context.getString(R.string.albums)
        2 -> context.getString(R.string.playlists)
        3 -> context.getString(R.string.favorites)
        else -> ""
      }
    }
  }
}