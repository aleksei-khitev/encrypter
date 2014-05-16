package ru.akhitev.encrypter.file

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.nio.charset.Charset

/**
 * Created by hitev on 29.04.14.
 */
class AesFileEncrypterImpl implements IFileEncrypter{
    /**
     * Метод шифрования
     */
    static final String CRYPTION_METHOD = "AES"

    static final String CHIPER_CRYPTION_METHOD = "AES/CBC/PKCS5Padding"
    /**
     * Кодировка ключа шифрования
     */
    static final String KEY_CHARSET = "windows-1251"
    /**
     * Длинна ключа
     */
    static final int BYTE_ARRAY_LENGTH = 16
    /**
     * Ключ шифрования
     */
    String cryptionKey
    /**
     * Исходный файл
     */
    File inputFile
    /**
     * Выходной файл
     */
    File outputFile

    /**
     * Шифорвание файла
     */
    @Override
    void encryptFile() {
        //Чтение файла в массив байт
        byte[] inputBytes = readBytesFromFile()
        //Создание шифратора
        Cipher cipher = Cipher.getInstance(CHIPER_CRYPTION_METHOD)
        cipher.init(Cipher.ENCRYPT_MODE, prepareCryptoKey(), new IvParameterSpec(new byte[BYTE_ARRAY_LENGTH]))
        //Запись в файл результата
        writeBytesToFile(cipher.doFinal(inputBytes))
    }

    /**
     * Дешифрование файла
     */
    @Override
    void decryptFile() {
        //Чтение из файла в массив байт
        byte[] inputBytes = readBytesFromFile()
        //Создание шифратора
        Cipher cipher = Cipher.getInstance(CHIPER_CRYPTION_METHOD);
        cipher.init(Cipher.DECRYPT_MODE, prepareCryptoKey(), new IvParameterSpec(new byte[BYTE_ARRAY_LENGTH]));
        //Вывод в файл результата
        writeBytesToFile(cipher.doFinal(inputBytes))
    }

    SecretKeySpec prepareCryptoKey(){
        byte[] raw = cryptionKey.getBytes(Charset.forName(KEY_CHARSET));
        if (raw.length != BYTE_ARRAY_LENGTH) {
            throw new IllegalArgumentException("Invalid key size.");
        }
        SecretKeySpec skeySpec = new SecretKeySpec(raw, CRYPTION_METHOD);
        return skeySpec
    }

    /**
     * Чтение файла в массив байт
     */
    Byte[] readBytesFromFile(){
        ByteArrayOutputStream ous = null
        InputStream ios = null
        try {
            byte[] buffer = new byte[4096]
            ous = new ByteArrayOutputStream()
            ios = new FileInputStream(inputFile)
            int read = 0
            while ((read = ios.read(buffer)) != -1)
                ous.write(buffer, 0, read)
        } finally {
            try {
                if (ous != null)
                    ous.close()
            } catch (IOException e) {
                // swallow, since not that important
            }
            try {
                if (ios != null)
                    ios.close()
            } catch (IOException e) {
                // swallow, since not that important
            }
        }
        return ous.toByteArray()
    }

    /**
     * Запись массива байт в файл
     * @param bytes Массив байт
     */
    void writeBytesToFile(byte[] bytes){
        FileOutputStream out = new FileOutputStream(outputFile);
        out.write(bytes);
    }
}
