package com.stanford.anglishwordbook.network;

/**
 * Implement this interface on everything that will be serialized by the default converter in the networkbuilder
 *
 * Use the @PreSerialize and @PostDeserialize annotations on the methods or it won't work bro.
 * Created by m.stanford on 3/5/15.
 */
public interface ISerializable {

    /**
     * Called after the class is deserialized into an object
     */
    void postDeserialize();

    /**
     * Called before the class is serialized.
     */
    void preSerialize();
}
