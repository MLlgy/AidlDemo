/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/liguoying/Git/AidlDemo/app/src/main/aidl/com/mk/aidldemo/IBookManager.aidl
 */
package com.mk.aidldemo.server;
// Declare any non-default types here with import statements

import android.util.Log;

import com.mk.aidldemo.util.ProcessUtils;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public interface IBookManager2 extends android.os.IInterface {
    /**
     * Local-side IPC implementation stub class.
     */
    public static abstract class Stub extends android.os.Binder implements IBookManager2 {
        private static final String DESCRIPTOR = "com.mk.aidldemo.IBookManager";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.mk.aidldemo.IBookManager interface,
         * generating a proxy if needed.
         */
        public static IBookManager2 asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            /**
             * queryLocalInterface
             *
             * Attempt to retrieve a local implementation of an interface
             * for this Binder object.  If null is returned, you will need
             * to instantiate a proxy class to marshall calls through
             * the transact() method.
             *
             * 对于这个绑定对象,尝试检索本地接口的实现.如果为 null，你需要初始化一个代理类，
             * 用它来调用 transact() 方法。
             */
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof IBookManager2))) {
                return ((IBookManager2) iin);
            }
            return new Stub.Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        /**
         * 运行在线程池中
         */
        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_getBookList: {
                    data.enforceInterface(descriptor);
                    java.util.List<com.mk.aidldemo.Book> _result = this.getBookList();
                    reply.writeNoException();
                    Log.e("process onTransact list", ProcessUtils.getCurrentProcessName());
                    reply.writeTypedList(_result);
                    return true;
                }
                case TRANSACTION_addBook: {
                    data.enforceInterface(descriptor);
                    com.mk.aidldemo.Book _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = com.mk.aidldemo.Book.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    // Service 中 onBinder 方法中返回的 Binder 对象值。
                    this.addBook(_arg0);
                    Log.e("process onTransact add", ProcessUtils.getCurrentProcessName());
                    reply.writeNoException();
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static class Proxy implements IBookManager2 {
            private android.os.IBinder mRemote;

            //为不同进程时，remote 为另一个进程中的对象,即为跨进程对象

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            /**
             * 运行在客户端
             */
            @Override
            public java.util.List<com.mk.aidldemo.Book> getBookList() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                java.util.List<com.mk.aidldemo.Book> _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    //通过 transact 将数据传给 Binder 的 Server 端，实现了跨进程，但是最终还是会调用 Stub 的 onTransact() 方法，但是在 Server 端
                    // 运算结果会通过 _reply 传递回来
                    Log.e("process proxy list", ProcessUtils.getCurrentProcessName());
                    mRemote.transact(Stub.TRANSACTION_getBookList, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.createTypedArrayList(com.mk.aidldemo.Book.CREATOR);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            /**
             * 运行在客户端
             */
            @Override
            public void addBook(com.mk.aidldemo.Book book) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((book != null)) {
                        _data.writeInt(1);
                        book.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    Log.e("process proxy add", ProcessUtils.getCurrentProcessName());
                    mRemote.transact(Stub.TRANSACTION_addBook, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    }

    public java.util.List<com.mk.aidldemo.Book> getBookList() throws android.os.RemoteException;

    public void addBook(com.mk.aidldemo.Book book) throws android.os.RemoteException;
}
