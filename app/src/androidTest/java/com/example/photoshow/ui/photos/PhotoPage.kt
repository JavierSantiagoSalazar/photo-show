package com.example.interrapidismotest.ui.menu

import com.example.interrapidismotest.R
import com.example.interrapidismotest.page.*


class MenuPage : Page() {

    fun verifyRedText(text: String): MenuPage {
        verifyTextInView(R.id.tvEmpty, text)
        return this
    }

    fun verifyGreenText(text: String): MenuPage {
        verifyTextInView(R.id.tvFull, text)
        return this
    }

    fun clickButtonById(buttonId: Int): MenuPage{
        clickButton(buttonId)
        return this
    }

    fun checkIfTextIsNotDisplayedById(id: Int): MenuPage{
        checkIfViewIsNotDisplayed(id)
        return this
    }

    fun checkIfListIsDisplayed(): MenuPage{
        checkIfViewIsDisplayed(R.id.recyclerTables)
        return this
    }

    fun verifyIfButtonChangeText(text: String): MenuPage{
        verifyTextInView(R.id.showDataButton, text)
        return this
    }


    fun checkIfListNotDisplayed(): MenuPage{
        checkIfViewIsNotDisplayed(R.id.recyclerTables)
        return this
    }

    fun clickInEditText(): MenuPage{
        clickViewById(R.id.etFilter)
        return this
    }

    fun typeTableName(): MenuPage{
        typeTextInView(R.id.etFilter, "TableName6")
        return this
    }

    fun verifyTableNameInRecycler(recyclerId: Int, tableName: String): MenuPage {
        verifyItemTextInRecyclerView(recyclerId, tableName)
        return this
    }
}
