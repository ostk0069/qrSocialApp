package jp.co.cyberagent.dojo2019.Presentation.UserIndex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.Model.UserModel

class UserIndexViewModel: ViewModel() {

    var mutableLiveData: MutableLiveData<MutableList<UserModel>> = MutableLiveData()
}