package com.jake.wukong.global;

/**
 * description：single instance of ResourceController,in
 *
 * @author jake
 * @since 2016/9/26 23:20
 */


public class ResourceController extends BaseResourceController {
    public static ResourceController getInstance() {
        return InstanceBuild.controller;
    }

    static class InstanceBuild {
        static ResourceController controller = new ResourceController();
    }
}
