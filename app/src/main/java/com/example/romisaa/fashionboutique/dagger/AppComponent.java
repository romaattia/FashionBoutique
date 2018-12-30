package com.example.romisaa.fashionboutique.dagger;

import com.example.romisaa.fashionboutique.presentation.view.about.edit_about.AboutViewModel;
import com.example.romisaa.fashionboutique.presentation.view.about.view_about.EditAboutViewModel;
import com.example.romisaa.fashionboutique.presentation.view.feedback.FeedbackViewModel;
import com.example.romisaa.fashionboutique.presentation.view.home.add_product.AddProductViewModel;
import com.example.romisaa.fashionboutique.presentation.view.home.view_product.ViewProductsViewModel;
import com.example.romisaa.fashionboutique.presentation.view.login.LoginViewModel;
import com.example.romisaa.fashionboutique.presentation.view.signup.SignupViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(ViewProductsViewModel viewModel);

    void inject(AddProductViewModel viewModel);

    void inject(LoginViewModel viewModel);

    void inject(SignupViewModel viewModel);

    void inject(FeedbackViewModel viewModel);

    void inject(AboutViewModel viewModel);

    void inject(EditAboutViewModel viewModel);
}
