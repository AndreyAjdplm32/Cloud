package Service;

import com.example.cloud.exceptions.RequestErrors;
import com.example.cloud.models.File;
import com.example.cloud.models.User;
import com.example.cloud.repository.FileStorageRepository;
import com.example.cloud.service.FileStorageService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
@SpringBootTest(classes = FileStorageService.class)
class FileServiceTest {

    @Mock
    FileStorageRepository repository;
    @InjectMocks
    FileStorageService fileService;
    static byte[] BYTES = {6, 6, 6};
    static final String FILENAME_1 = "file1";
    static final String FILENAME_2 = "file2";


    @Test
    void getFile() {
        //arrange
        byte[] savedFile = {1, 2, 3};
        when(repository.findByFileName("file")).thenReturn(Optional.of(new File(1L, "file", "type", "path", savedFile, new User())));
        //act
        byte[] file = fileService.getFile("file");
        //assert
        assertArrayEquals(savedFile, file);
    }

    @Test
    void getFiles() {
        //arrange
        List<File> respond = new ArrayList<>();
        respond.add(new File(1L, "file", "path", "type", BYTES, new User()));
        respond.add(new File(2L, "file2", "path", "type", BYTES, new User()));

        List<File> request = new ArrayList<>();
        request.add(new File(1L, "file", "path", "type", BYTES, new User()));
        request.add(new File(2L, "file2", "path", "type", BYTES, new User()));
        when(repository.findAll()).thenReturn(request);
        //act
        List<File> result = fileService.getAllFiles();
        //assert
        assertThat(respond, is(result));
    }

    @Test
    void removeFile() {
        //arrange
        when(repository.findByFileName(FILENAME_1)).thenReturn(Optional.empty());
        //act-assert
        Assertions.assertThrows(RequestErrors.class, () -> fileService.deleteFile(FILENAME_1));
    }

    @Test
    void updateFile() {
        //arrange
        when(repository.findByFileName(FILENAME_1)).thenReturn(Optional.empty());
        //act-assert
        Assertions.assertThrows(RequestErrors.class,() -> fileService.editFileName(FILENAME_1,FILENAME_2));
    }


}
