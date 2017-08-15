/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package yolung.cardinalblue.com.mvp_pattern.addnote;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import yolung.cardinalblue.com.mvp_pattern.data.Note;
import yolung.cardinalblue.com.mvp_pattern.data.NotesRepository;
import yolung.cardinalblue.com.mvp_pattern.util.ImageFile;

/**
 * Listens to user actions from the UI ({@link AddNoteFragment}), retrieves the data and updates
 * the UI as required.
 */
public class AddNotePresenter implements AddNoteContract.UserActionsListener {

    @NonNull
    private final NotesRepository mNotesRepository;
    @NonNull
    private final AddNoteContract.View mAddNoteView;
    @NonNull
    private final ImageFile mImageFile;

    public AddNotePresenter(@NonNull NotesRepository notesRepository,
                            @NonNull AddNoteContract.View addNoteView,
                            @NonNull ImageFile imageFile) {
        mNotesRepository = checkNotNull(notesRepository);
        mAddNoteView = checkNotNull(addNoteView);
        mImageFile = imageFile;
    }

    @Override
    public Observable<Boolean> saveNote(final String title,
                                  final String description) {
        final String imageUrl;
        if (mImageFile.exists()) {
            imageUrl = mImageFile.getPath();
        } else {
            imageUrl = null;
        }

        return Observable.fromCallable(
            new Callable<XxxEvent>() {
                @Override
                public XxxEvent call() throws Exception {
                    // Before.
    //                Note newNote = new Note(title, description, imageUrl);
    //                if (newNote.isEmpty()) {
    //                    mAddNoteView.showEmptyNoteError();
    //                } else {
    //                    mNotesRepository.saveNote(newNote);
    //                    mAddNoteView.showNotesList();
    //                }

                    // After.
                    final Note newNote = new Note(title, description, imageUrl);
                    if (newNote.isEmpty()) {
                        return new XxxEvent(XxxEvent.F);
                    } else {
                        mNotesRepository.saveNote(newNote);
                        return new XxxEvent(XxxEvent.S);
                    }
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(new Function<XxxEvent, ObservableSource<Boolean>>() {
                @Override
                public ObservableSource<Boolean> apply(
                    @io.reactivex.annotations.NonNull XxxEvent event)
                    throws Exception {

                    if (event.status == XxxEvent.S) {
                        mAddNoteView.showNotesList();

                        return Observable.just(true);
                    } else {
                        mAddNoteView.showEmptyNoteError();

                        return Observable.just(false);
                    }
                }
            });
    }

    @Override
    public void takePicture() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        mImageFile.create(imageFileName, ".jpg");
        mAddNoteView.openCamera(mImageFile.getPath());
    }

    @Override
    public void imageAvailable() {
        if (mImageFile.exists()) {
            mAddNoteView.showImagePreview(mImageFile.getPath());
        } else {
            imageCaptureFailed();
        }
    }

    @Override
    public void imageCaptureFailed() {
        captureFailed();
    }

    private void captureFailed() {
        mImageFile.delete();
        mAddNoteView.showImageError();
    }

    public static class XxxEvent {

        public static final int S = 0;
        public static final int F = 1;

        public int status = 0;

        public XxxEvent(final int status) {
            this.status = status;
        }
    }
}
