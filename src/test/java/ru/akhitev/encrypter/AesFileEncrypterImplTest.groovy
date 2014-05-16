package ru.akhitev.encrypter.fileEcryption

import org.junit.Before
import org.junit.Test
import ru.akhitev.encrypter.file.AesFileEncrypterImpl
import ru.akhitev.encrypter.file.IFileEncrypter

class AesFileEncrypterImplTest {
    File inputFile = null
    File encryptedFile = null
    File decryptedFile = null
    String key = ""

    @Before
    void init(){
        key = "ThisIsASecretKey"
        inputFile = new File("test/input.txt")
        encryptedFile = new File("test/encrypted.kvz")
        decryptedFile = new File("test/decrypted.txt")
    }

    @Test
    void encryptTest(){
        IFileEncrypter coder = new AesFileEncrypterImpl()
        coder.setCryptionKey(key)
        coder.inputFile=inputFile
        coder.outputFile=encryptedFile
        coder.encryptFile()
    }

    @Test
    void decryptTest(){
        IFileEncrypter coder = new AesFileEncrypterImpl()
        coder.setCryptionKey(key)
        coder.inputFile=encryptedFile
        coder.outputFile=decryptedFile
        coder.decryptFile()
    }


}