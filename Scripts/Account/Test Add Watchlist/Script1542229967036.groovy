import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static groovy.json.JsonOutput.toJson
import internal.GlobalVariable as GlobalVariable
import theMovieDB.TheMovieDBCommon as TheMovieDBCommon

TheMovieDBCommon common = new TheMovieDBCommon()

String sessionID = common.getSessionID()

//Test Get Created List
RequestObject reqObj = findTestObject('Account/Add Watchlist')

//String myStr = reqObj.getRestUrl()
//String myURL = myStr.split("\\?")[0]
//myURL += "?api_key=" + GlobalVariable.apiKey
//myURL += "&session_id=" + sessionID

List<TestObjectProperty> params = new ArrayList()
params.add(new TestObjectProperty("api_key",ConditionType.EQUALS, GlobalVariable.apiKey))
params.add(new TestObjectProperty("session_id",ConditionType.EQUALS, sessionID))
reqObj.setRestParameters(params)

String myStr = reqObj.getRestUrl()

println("************ my URL: "+ myStr)
//reqObj.setRestUrl(myURL)
String body = toJson(media_type: 'movie', media_id: 13, watchlist: true)
reqObj.setBodyContent(new HttpTextBodyContent(body))

ResponseObject response1 = WS.sendRequest(reqObj)

common.printDataValue(response1, "Account/Add Watchlist")
