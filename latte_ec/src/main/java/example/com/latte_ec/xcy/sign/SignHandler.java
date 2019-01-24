package example.com.latte_ec.xcy.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import example.com.latte_core.app.AccountManager;
import example.com.latte_ec.xcy.database.DatabaseManager;
import example.com.latte_ec.xcy.database.UserProfile;

/**
 * created by xcy on 2019/1/23
 **/
public class SignHandler {

    public static void onSignIn(String response,ISignListener iSignListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId,name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insertOrReplace(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        iSignListener.onSignInSuccess();
    }

    public static void onSignUp(String response,ISignListener iSignListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId,name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insertOrReplace(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        iSignListener.onSignUpSuccess();
    }
}
