package com.example.proiectsma

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun StudyingPlatformNavigationToScreens(
    authentificationViewModel: AuthentificationViewModel,
) {
    val navigation: NavHostController = rememberNavController()

    NavHost(
        navController = navigation,
        startDestination = "studyingPlatformHomeScreen"
    ) {
        composable("studyingPlatformHomeScreen"){
            StudyingPlatformHomeScreen(navigation, authentificationViewModel)
        }

        composable("studyingPlatformLoginScreen") {
            StudyingPlatformLoginScreen(navigation, authentificationViewModel)
        }

        composable("studyingPlatformSignUpScreen") {
            StudyingPlatformSignUpScreen(navigation, authentificationViewModel)
        }

        composable("studyingPlatformSubjectsScreen") {

            StudyingPlatformSubjectsScreen(navigation,
                onStartSessionClick = {},
                onAssignmentObjectClick = {},
                onSubjectObjectClick = {})
        }

        composable("studyingPlatformSubjectScreen"){
            StudyingPlatformSubjectScreen(navigation)
        }

        composable("assignmentScreen"){
            AssignmentScreen(navigation)
        }

        composable("sessionScreen"){
            SessionScreen(navigation)
        }
    }
}