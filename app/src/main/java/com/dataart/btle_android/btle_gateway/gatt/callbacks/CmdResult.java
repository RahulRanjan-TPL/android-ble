package com.dataart.btle_android.btle_gateway.gatt.callbacks;

import android.content.Context;

import com.dataart.android.devicehive.device.CommandResult;
import com.dataart.btle_android.BTLEApplication;
import com.dataart.btle_android.R;
import com.google.gson.Gson;

/**
 * Created by Constantine Mars on 4/8/15.
 *
 * Formatter for json command results
 */
public class CmdResult {
    protected String serviceUUID;
    protected String characteristicUUID;
    protected String device;

    public CmdResult(String serviceUUID, String characteristicUUID, String device, Context context) {
        this.serviceUUID = serviceUUID;
        this.characteristicUUID = characteristicUUID;
        this.device = device;
        this.context = context;
    }

    protected Context context;

    private  String jsonFullStatus(int statusStringId) {
        return jsonFullStatus(context.getString(statusStringId));
    }

    private static String jsonStatusOk() {
        return new Gson().toJson(StatusJson.Status.statusOk());
    }

    private static String jsonStatusOkWithVal(String val) {
        return new Gson().toJson(StatusJson.Status.statusOkWithVal(val));
    }

    private static String jsonStatusFail() {
        return new Gson().toJson(StatusJson.Status.statusFail());
    }

    private static String jsonStatusFailWithVal(String val) {
        return new Gson().toJson(StatusJson.Status.statusFailWithVal(val));
    }

    private static String jsonStatusTimeoutReached() {
        return new Gson().toJson(StatusJson.Status.statusTimeoutReached());
    }

    private static String jsonStatus(int strResId){
        return new Gson().toJson(new StatusJson.Status(BTLEApplication.getApplication().getString(strResId)));
    }

    private String jsonFullStatus(String status) {
        return new Gson().toJson(new StatusJson.FullStatus(
                status,
                device,
                serviceUUID,
                characteristicUUID
        ));
    }

    private String jsonFullStatusWithValue(String status, byte[] value) {
        return new Gson().toJson(new StatusJson.FullStatusWithValue(
                status,
                value,
                device,
                serviceUUID,
                characteristicUUID
        ));
    }

    private String jsonFullStatusWithValue(int statusStringId, byte[] value) {
        return jsonFullStatusWithValue(context.getString(statusStringId), value);
    }

    public static CommandResult success() {
        return new CommandResult(CommandResult.STATUS_COMLETED, jsonStatusOk());
    }

    public static CommandResult successWithVal(String val) {
        return new CommandResult(CommandResult.STATUS_COMLETED, jsonStatusOkWithVal(val));
    }

    public static CommandResult commandResultFail() {
        return new CommandResult(CommandResult.STATUS_FAILED, jsonStatusFail());
    }

    public static CommandResult failWithStatus(String val) {
        return new CommandResult(CommandResult.STATUS_FAILED, jsonStatusFailWithVal(val));
    }

    public static CommandResult failWithStatus(int strResId) {
        return new CommandResult(CommandResult.STATUS_FAILED, jsonStatus(strResId));
    }

    public static CommandResult failTimeoutReached() {
        return new CommandResult(CommandResult.STATUS_FAILED, jsonStatusTimeoutReached());
    }

    protected CommandResult cmdResFullSuccess() {
        return new CommandResult(CommandResult.STATUS_COMLETED, jsonFullStatus(R.string.status_json_success));
    }

    public CommandResult successFullWithVal(byte[] value) {
        return new CommandResult(CommandResult.STATUS_COMLETED, jsonFullStatusWithValue(R.string.status_json_success, value));
    }

    public CommandResult successFullWithStatus(String status) {
        return new CommandResult(CommandResult.STATUS_COMLETED, jsonFullStatus(status));
    }

    protected CommandResult cmdResFullFail() {
        return new CommandResult(CommandResult.STATUS_FAILED, jsonFullStatus(R.string.status_json_fail));
    }

    public CommandResult cmdResFullFailStatus(String status) {
        return new CommandResult(CommandResult.STATUS_FAILED, jsonFullStatus(status));
    }

    protected CommandResult cmdResFullFailValue(byte[] value) {
        return new CommandResult(CommandResult.STATUS_FAILED, jsonFullStatusWithValue(R.string.status_json_fail, value));
    }

    protected CommandResult cmdResFullFailStatusAndValue(String status, byte[] value) {
        return new CommandResult(CommandResult.STATUS_FAILED, jsonFullStatusWithValue(status, value));
    }

    protected CommandResult cmdResFullNotFound() {
        return new CommandResult(CommandResult.STATUS_FAILED, jsonFullStatus(R.string.status_json_not_found));
    }
}