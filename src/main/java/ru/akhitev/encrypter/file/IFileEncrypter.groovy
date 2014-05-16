/*
 * ru.akhitev.encrypter is a library for encryption.
 * Copyright (c) 2014 Aleksei Khitev (Хитёв Алексей Юрьевич).
 *
 * This file is part of ru.akhitev.encrypter
 *
 * ru.akhitev.encrypter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * ru.akhitev.encrypter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package ru.akhitev.encrypter.file

import org.apache.log4j.Logger

/**
 * The interface used for encrypting and decrypting files
 *
 * @author Aleksei Khitev (alexkhitev@gmail.com)
 */
interface IFileEncrypter{

    /**
     * The method used for setting encryption key
     * @param encryptionKey Encryption key
     */
    void setCryptionKey(String cryptionKey)

    /**
     * The method used for setting input file
     * @param inputFile Input File
     */
    void setInputFile(File inputFile)

    /**
     * The method used for setting output file
     * @param outputFile Output file
     */
    void setOutputFile(File outputFile)

    /**
     * The method used for encrypting file
     */
    void encryptFile()

    /**
     * The method used for decrypting file
     */
    void decryptFile()

    /**
     * The method used for setting logger
     * @param logger
     */
    void setLogger(Logger logger)
}