package ru.konverdev.telegram.ui.objects

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import ru.konverdev.telegram.R
import ru.konverdev.telegram.ui.fragments.SettingsFragment

class AppDrawer(val mainActivity: AppCompatActivity, private val toolbar: Toolbar) {
    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader

    fun create() {
        createHeader()
        createDrawer()
    }

    private fun createDrawer() {
        mDrawer = DrawerBuilder()
            .withActivity(mainActivity)
            .withToolbar(toolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                createSimplePrimaryDrawerItem(100, "Создать группу", R.drawable.ic_menu_create_groups),
                createSimplePrimaryDrawerItem(101, "Создать секретный чат", R.drawable.ic_menu_secret_chat),
                createSimplePrimaryDrawerItem(102, "Создать канал", R.drawable.ic_menu_create_channel),
                createSimplePrimaryDrawerItem(103, "Контакты", R.drawable.ic_menu_contacts),
                createSimplePrimaryDrawerItem(104, "Звонки", R.drawable.ic_menu_phone),
                createSimplePrimaryDrawerItem(105, "Избранное", R.drawable.ic_menu_favorites),
                createSimplePrimaryDrawerItem(106, "Настройки", R.drawable.ic_menu_settings),
                DividerDrawerItem(),
                createSimplePrimaryDrawerItem(107, "Пригласить друзей", R.drawable.ic_menu_invate),
                createSimplePrimaryDrawerItem(108, "Вопросы о Telegram", R.drawable.ic_menu_help)
            )
            .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    when (drawerItem.identifier.toInt()) {
                        106 -> mainActivity.supportFragmentManager.beginTransaction().addToBackStack(null)
                            .replace(R.id.dataContainer, SettingsFragment()).commit()
                    }
                    return false
                }
            })
            .build()
    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
            .withActivity(mainActivity)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(
                ProfileDrawerItem()
                    .withName("Рустам Халилов")
                    .withEmail("+79963035500")
            ).build()
    }

    private fun createSimplePrimaryDrawerItem(identifier: Long, name: String, icon: Int): PrimaryDrawerItem {
        return PrimaryDrawerItem()
            .withIdentifier(identifier)
            .withIconTintingEnabled(true)
            .withName(name)
            .withIcon(icon)
            .withSelectable(false)
    }
}