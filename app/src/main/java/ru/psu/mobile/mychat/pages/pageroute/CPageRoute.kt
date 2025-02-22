package ru.psu.mobile.mychat.pages.pageroute

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.psu.mobile.mychat.model.CCheckPoint
import java.util.UUID
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.psu.mobile.mychat.R
import ru.psu.mobile.mychat.pages.layout.CViewModelLayout

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun CPageRoute(
    navController                           : NavController,
    modifier                                : Modifier = Modifier
)
{
    val viewModelLayout                     : CViewModelLayout
                                            = viewModel(LocalActivity.current as ComponentActivity)
    viewModelLayout.setPageName(stringResource(R.string.Route))


    val viewModel                           : CViewModelPageRoute
                                            = viewModel()
    val checkPoints by viewModel.checkpoints.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize().padding(20.dp),
    ){
        LazyColumn(
            //modifier = Modifier.padding(innerPadding)
        ) {
            items(
                checkPoints,
                key = { checkpoint -> checkpoint.id }
            ) { checkpoint ->
                CheckPointRow(
                    checkpoint,
                    onClick = {checkpoint1 ->
                        navController.navigate("checkpointinfo/${checkpoint1.id}")
                    },
                    onLongClick = { checkpoint1 ->
                        viewModel.remove(checkpoint1)
                    }
                )
            }
            items(2) {
                Text(text="")
            }
        }

        Row(modifier = Modifier.align(Alignment.BottomEnd)){
            FloatingActionButton(
                onClick = {
                    viewModel.addCheckPoint(
                        CCheckPoint(
                            UUID.randomUUID(),
                            "Контрольная точка akdmkadadada ad ad ad",
                            "флвьфджлв",
                            "adadadadadawd"
                        )
                    )
                },
            )
            {
                Icon(Icons.Filled.Add, contentDescription = "Add Button")
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckPointRow(
    checkPoint: CCheckPoint,
    onClick : (CCheckPoint) -> Unit,
    onLongClick: (CCheckPoint) -> Unit,
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onLongClick = {
                    onLongClick(checkPoint)
                }
            ){
                onClick(checkPoint)
            }
    )
    {
        Text(
            text = "${checkPoint.name} ${checkPoint.id}",
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = checkPoint.lat,
            modifier = Modifier
                .width(60.dp)
        )
        Text(
            text = checkPoint.lon,
            modifier = Modifier
                .width(60.dp)
        )
    }


}