package com.box.androidsdk.share.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.box.androidsdk.content.models.BoxCollaboration;
import com.box.androidsdk.content.models.BoxCollaborationItem;
import com.box.androidsdk.content.models.BoxItem;
import com.box.androidsdk.content.models.BoxIteratorCollaborations;
import com.box.androidsdk.content.models.BoxSharedLink;
import com.box.androidsdk.content.models.BoxVoid;
import com.box.androidsdk.content.views.BoxAvatarView;
import com.box.androidsdk.share.internal.models.BoxFeatures;
import com.box.androidsdk.share.sharerepo.ShareRepo;
import com.box.androidsdk.share.utils.ShareSDKTransformer;

import java.sql.Date;

public class SharedLinkVM extends BaseShareVM {

    private final LiveData<PresenterData<BoxItem>> mShareLinkedItem;
    private final LiveData<PresenterData<BoxFeatures>> mSupportedFeatures;


    public SharedLinkVM(ShareRepo shareRepo, BoxCollaborationItem shareItem) {
        super(shareRepo, shareItem);
        ShareSDKTransformer transformer = new ShareSDKTransformer();
        mShareLinkedItem = Transformations.map(shareRepo.getShareLinkedItem(),
                response -> transformer.getSharedLinkItem(response, getShareItem()));

        mSupportedFeatures = new MutableLiveData<>();

    }



    public void createDefaultSharedLinkRemote(BoxCollaborationItem item) {
        mShareRepo.createDefaultSharedLink(item);
    }

    public void disableSharedLinkRemote(BoxCollaborationItem item) {
        mShareRepo.disableSharedLink(item);
    }

    public void changeDownloadPermissionRemote(BoxCollaborationItem item, boolean canDownload) {
        mShareRepo.changeDownloadPermission(item, canDownload);
    }

    public void setExpiryDateRemote(BoxCollaborationItem item, Date date) throws Exception {
        mShareRepo.setExpiryDate(item, date);
    }

    public void changeAccessLevelRemote(BoxCollaborationItem item, BoxSharedLink.Access access) {
        mShareRepo.changeAccessLevel(item, access);
    }

    public void changePasswordRemote(BoxCollaborationItem item, String password) {
        mShareRepo.changePassword(item, password);
    }



    public void fetchSupportedFeaturesRemote() {
        mShareRepo.fetchSupportedFeatures();
    }
}
