package com.example.photoshow.ui.photos

import com.example.photoshow.R
import com.example.photoshow.data.database.PhotoDao
import com.example.photoshow.data.database.PhotoShowDatabase
import com.example.photoshow.page.BaseUiTest
import com.example.photoshow.page.Page.Companion.on
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class PhotosFragmentTest: BaseUiTest() {

    @Inject
    lateinit var database: PhotoShowDatabase

    @Inject
    lateinit var tableDao: PhotoDao

    @Test
    fun start_activity_shows_the_title_and_photos() {
        on<PhotoPage>()
            .wait(10)
            .on<PhotoPage>()
            .verifyTitleText(R.id.tvTitleFragmentPhoto,"Photos")
            .verifyPhotoTitleInRecycler(R.id.recyclerPhotos, "Photo Title 1")
    }

    @Test
    fun click_a_photo_navigates_to_detail() {
        on<PhotoPage>()
            .wait(1)
            .on<PhotoPage>()
            .clickInPhotoRecyclerItem(R.id.recyclerPhotos, 3)
            .verifyToolbarTitle("Detail")
            .verifyPhotoTitle("Photo Title 4")
            .verifyPhotoId("4")
            .verifyAlbumId("4")
            .verifyText(R.id.tvDeletePhotoText, "If you want to delete\n this photo, press here:")
    }

    @Test
    fun click_in_back_button_navigates_to_photos() {
        on<PhotoPage>()
            .wait(1)
            .on<PhotoPage>()
            .clickInPhotoRecyclerItem(R.id.recyclerPhotos, 3)
            .verifyToolbarTitle("Detail")
            .verifyPhotoTitle("Photo Title 4")
            .verifyPhotoId("4")
            .verifyAlbumId("4")
            .verifyText(R.id.tvDeletePhotoText, "If you want to delete\n this photo, press here:")
            .back()
            .on<PhotoPage>()
            .verifyTitleText(R.id.tvTitleFragmentPhoto,"Photos")
            .verifyPhotoTitleInRecycler(R.id.recyclerPhotos, "Photo Title 1")
    }

    @Test
    fun click_in_delete_button_navigates_to_photos_and_delete_the_photo() {
        on<PhotoPage>()
            .wait(1)
            .on<PhotoPage>()
            .clickInPhotoRecyclerItem(R.id.recyclerPhotos, 3)
            .verifyToolbarTitle("Detail")
            .verifyPhotoTitle("Photo Title 4")
            .verifyPhotoId("4")
            .verifyAlbumId("4")
            .verifyText(R.id.tvDeletePhotoText, "If you want to delete\n this photo, press here:")
            .clickDeleteButton()
            .clickYesButton()
            .verifyTitleText(R.id.tvTitleFragmentPhoto,"Photos")
            .verifyPhotoWasDeleted("Photo Title 4")
    }
}
