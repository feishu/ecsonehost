package com.ucmed.mrdc.tslimage.imagepicker.weex_module;

import java.util.List;

/**
 * Created by ucmed on 2016/8/1.
 */
public interface ImagePickerListener {

    public void pickerFinish(List<String> path);

    public void DeleteImage(int index);

}
