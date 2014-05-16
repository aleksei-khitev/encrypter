package ru.akhitev.encrypter.file

interface IFileEncrypter{
    /**
     * Интерфейс для шифрования файлов
     *
     * @author Хитёв Алексей Юрьевич (alexkhitev@gmail.com)
     */
    interface IFileCrypter {
        /**
         * Метод задачи ключа
         * @param encryptionKey Ключ шифрования
         */
        void setCryptionKey(String cryptionKey)
        /**
         * Метод задачи исходного файла
         * @param inputFile Исходный файл
         */
        void setInputFile(File inputFile)
        /**
         * Метод задачи выходного файла
         * @param outputFile Выходной файл
         */
        void setOutputFile(File outputFile)
        /**
         * Шифорвание файла
         */
        void encryptFile()
        /**
         * Дешифрование файла
         */
        void decryptFile()
    }
}