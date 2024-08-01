package com.example.compose.rally.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.rally.Accounts
import com.example.compose.rally.Bills
import com.example.compose.rally.Overview
import com.example.compose.rally.SingleAccount
import com.example.compose.rally.ui.accounts.AccountsScreen
import com.example.compose.rally.ui.accounts.SingleAccountScreen
import com.example.compose.rally.ui.bills.BillsScreen
import com.example.compose.rally.ui.overview.OverviewScreen

@Composable
fun RallyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ) {
        composable(route = Overview.route) {
            OverviewScreen(onClickSeeAllAccounts = {
                navController.navigateSingleTopTo(
                    Accounts.route
                )
            }, onClickSeeAllBills = {
                navController.navigateSingleTopTo(Bills.route)
            }, onAccountClick = { accountType ->
                navController.navigateToSingleAccount(accountType)
            })
        }
        composable(route = Accounts.route) {
            AccountsScreen(onAccountClick = { accountType ->
                navController.navigateToSingleAccount(accountType)
            })
        }
        composable(route = Bills.route) {
            BillsScreen()
        }
        composable(
            route = SingleAccount.routeWithArgs,
            arguments = SingleAccount.arguments,
            deepLinks = SingleAccount.deepLinks
        )
        { navBackStackEntry ->
            // Retrieve the passed argument
            val accountType =
                navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
            // Pass accountType to SingleAccountScreen
            SingleAccountScreen(accountType)
        }
    }
}