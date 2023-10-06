package com.example.photoshow.ui.photos

import com.example.photoshow.R
import com.example.photoshow.page.Page
import com.example.photoshow.page.clickButton
import com.example.photoshow.page.clickInRecyclerItem
import com.example.photoshow.page.clickOnText
import com.example.photoshow.page.verifyItemTextInRecyclerView
import com.example.photoshow.page.verifyTextInChild
import com.example.photoshow.page.verifyTextInView
import com.example.photoshow.page.verifyTextIsNotDisplayed


class PhotoPage : Page() {

    fun verifyTitleText(view: Int, text: String): PhotoPage {
        verifyTextInView(view, text)
        return this
    }

    fun verifyPhotoWasDeleted(photoTitle: String): PhotoPage {
        verifyTextIsNotDisplayed(photoTitle)
        return this
    }

    fun clickDeleteButton(): PhotoPage {
        clickButton(R.id.fabDelete)
        return this
    }

    fun clickYesButton(): PhotoPage {
        clickOnText("Yes")
        return this
    }

    fun verifyToolbarTitle(title: String): PhotoPage {
        verifyTextInChild(R.id.toolbar, title)
        return this
    }

    fun verifyText(view: Int, text: String): PhotoPage {
        verifyTextInView(view, text)
        return this
    }

    fun verifyPhotoTitleInRecycler(recyclerId: Int, title: String): PhotoPage {
        verifyItemTextInRecyclerView(recyclerId, title)
        return this
    }

    fun clickInPhotoRecyclerItem(recyclerId: Int, itemPosition: Int): PhotoPage {
        clickInRecyclerItem(recyclerId, itemPosition)
        return this
    }

    fun verifyPhotoTitle(photoTitle: String): PhotoPage {
        verifyTextInView(R.id.tvTitle, photoTitle)
        return this
    }

    fun verifyPhotoId(photoId: String): PhotoPage {
        verifyTextInView(R.id.tvPhotoId, photoId)
        return this
    }

    fun verifyAlbumId(albumId: String): PhotoPage {
        verifyTextInView(R.id.tvAlbumId, albumId)
        return this
    }
}
