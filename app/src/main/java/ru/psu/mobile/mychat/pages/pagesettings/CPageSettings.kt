package ru.psu.mobile.mychat.pages.pagesettings

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.psu.mobile.mychat.R
import ru.psu.mobile.mychat.pages.layout.CViewModelLayout
import ru.psu.mobile.mychat.ui.theme.MychatTheme

@Composable
fun CPageSettings(
)
{
    //Переименование страницы
    val viewModelLayout                     : CViewModelLayout
                                            = viewModel(LocalActivity.current as ComponentActivity)
    viewModelLayout.setPageName(stringResource(R.string.Settings))

    val viewModel                           : CViewModelPageSettings
                                            = viewModel()
    val username by viewModel.username.collectAsState()

    val sendReportAutomatically by viewModel.sendReportAutomatically.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(5.dp)),
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = {viewModel.saveUsername(it) },

            label = {
                Text(stringResource(R.string.username))
            },
            modifier = Modifier
                .fillMaxWidth()
        )
//        Row(
//            modifier = Modifier
//                .height(56.dp)
//                .border(
//                    border = ButtonDefaults.outlinedButtonBorder(),
//                    shape = RoundedCornerShape(5.dp)
//
//                )
//                .fillMaxWidth()
//                .padding(PaddingValues(horizontal = 8.dp)),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween,
//        )
//        {
//            Text(stringResource(R.string.username))
//            Text(text = username)
//        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .height(56.dp)
                .border(
                    border = ButtonDefaults.outlinedButtonBorder(),
                    shape = RoundedCornerShape(5.dp)

                )
                .fillMaxWidth()
                .padding(PaddingValues(horizontal = 8.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        )
        {
            Text(stringResource(R.string.sendReportAutomatically))
            Switch(
                checked = sendReportAutomatically,
                onCheckedChange = {
                    viewModel.saveSendReportAutomatically(it)
                }
            )
        }

    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 480)
@Composable
fun GreetingPreview1() {
    MychatTheme {
        CPageSettings()
    }
}