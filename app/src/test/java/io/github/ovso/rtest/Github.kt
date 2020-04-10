@file:Suppress("NonAsciiCharacters")

package io.github.ovso.rtest

import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.data.network.model.Stargazer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

class Github {
  private val repo by lazy { GithubRepository() }

  @Test
  fun `깃허브 사용자 저장소 목록 가져오기`() {

    fun onSuccess(items: List<Repo>) {
      println(items.count())
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    repo.api().userRepos(User.name, 1, 3)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }

  @Test
  fun `깃허브 저장소에 스타를 준 stargazer 목록`() {

    fun onSuccess(items: List<Stargazer>) {
      println(items.size)
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    repo.api().stargazers(User.name, "android-PermissionRequest", 1, 3)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }

  @Test
  fun `깃허브 사용자 저장소 목록과 목록별 스타게이저 정보 가져오기`() {

    fun onSuccess(items: Any) {
      println(items.toString())
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

  }


  object SchedulerProvider {
    fun io(): Scheduler = Schedulers.trampoline()
    fun ui(): Scheduler = Schedulers.trampoline()
  }
}

/*

    private fun reqAccountsAndBalance() {
        prevAccountPosition = 0
        fun onSuccess(_items: MutableList<Account>) {
            itemsLive.value = mutableListOf<Account?>().apply { addAll(_items); add(null) }
        }

        fun onFailure(t: Throwable) {
            itemsLive.value = mutableListOf<Account?>().apply { add(null) }
            showErrorDialogLive.value = ((t as? HttpException)?.response()?.errorBody()?.string())
        }

        fun reqBalance(account: Account) =
            accountBankRequest
                .api()
                .balance(
                    account.bankCodeStd!!,
                    account.accountNum!!
                )
                .map { account.apply { balance = it } }
                .onErrorReturnItem(account)

        compositeDisposable += accountBankRequest
            .api()
            .getAccounts()
            .flatMapObservable { Observable.fromIterable(it) }
            .flatMapSingle { reqBalance(it) }
            .toList()
            .doOnSubscribe { showLoading() }
            .doOnError { hideLoading() }
            .doOnSuccess { hideLoading() }
            .subscribeOn(SchedulerProvider.io())
            .observeOn(SchedulerProvider.ui())
            .subscribe(::onSuccess, ::onFailure)
    }



 */
