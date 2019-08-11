package jp.co.cyberagent.dojo2019.Presentation.UserList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.Model.UserModel

class UserListViewModel: ViewModel() {

    var mutableLiveData: MutableLiveData<MutableList<UserModel>> = MutableLiveData()
}