package com.example.proiectsma

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

fun LazyListScope.SessionList(
    SessionTitle: String,
    SessionEmptyListText: String,
    SessionList: List<Session>,
    onDeleteSessionClick: (Session) -> Unit
){
    item{
        Text(
            text = SessionTitle,
            modifier = Modifier.padding(12.dp),
            fontWeight = FontWeight.Bold
        )
    }

    if(SessionList.isEmpty()){
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = SessionEmptyListText,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    items(SessionList){
            session ->
            SessionObject(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                session = session,
                onDeleteSessionClick = {onDeleteSessionClick(session)}
            )
    }
}

@Composable
fun SessionObject(
    modifier: Modifier = Modifier,
    session: Session,
    onDeleteSessionClick: () -> Unit
){
    Card(
        modifier = modifier.padding().border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column{
                Text(
                    text = session.SessionSubject,
                    modifier = Modifier.offset(x = 15.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${session.SessionDate}",
                    modifier = Modifier.offset(x = 15.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "${session.SessionDuration} hr"
            )

            IconButton(onClick = onDeleteSessionClick){
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete de subject session",
                    tint = Color(0xFFFF8B00)
                    )
            }
        }
    }
}