package com.jsonkile.testin.endtoend

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import com.jsonkile.testin.ui.composables.screens.home.loading_indication_test_tag
import com.jsonkile.testin.ui.composables.screens.home.results_section_test_tag
import com.jsonkile.testin.ui.composables.screens.home.search_button_test_tag
import com.jsonkile.testin.ui.composables.screens.home.search_text_field_test_tag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val LAUNCH_TIMEOUT = 10000L
private const val PACKAGE = "com.jsonkile.testin"

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class AppStartUpTest {

    private lateinit var uiDevice: UiDevice

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun startApp() {
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        uiDevice.pressHome()

        val launcherPackage = uiDevice.launcherPackageName
        Assert.assertNotNull(launcherPackage)
        uiDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT)

        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(PACKAGE)?.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        uiDevice.wait(Until.hasObject(By.pkg(PACKAGE).depth(0)), LAUNCH_TIMEOUT)
    }

    @Test
    fun testHomeScreenAppearsCorrectly() {
        val homeSearchField: UiObject2 = uiDevice.findObject(By.res(search_text_field_test_tag))
        val homeSearchButton: UiObject2 = uiDevice.findObject(By.res(search_button_test_tag))

        homeSearchField.text = "Avengers"
        homeSearchButton.click()

        Assert.assertEquals(
            true,
            uiDevice.wait(
                Until.hasObject(By.res(results_section_test_tag)),
                LAUNCH_TIMEOUT
            )
        )

    }
}
