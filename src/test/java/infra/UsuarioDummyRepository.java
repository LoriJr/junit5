package infra;

import com.viratech.domain.Usuario;
import com.viratech.service.repositories.UsuarioRepository;
import domain.builders.UsuarioBuilder;

import java.util.Optional;

public class UsuarioDummyRepository implements UsuarioRepository {
    @Override
    public Usuario salvar(Usuario usuario) {
        return UsuarioBuilder.umUsuario()
                .comNome(usuario.getNome())
                .comEmail(usuario.getEmail())
                .comSenha(usuario.getSenha())
                .agora();
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        return Optional.of(
                UsuarioBuilder.umUsuario().comEmail(email).agora());
    }
}
