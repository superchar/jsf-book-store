package com.dataart.booksapp.domain.general;

import com.dataart.booksapp.domain.general.exceptions.NotExistsException;

import java.util.function.Function;

/**
 * Created by vlobyntsev on 17.06.2016.
 */
public class GeneralMapper {

    public static <TModel, TViewModel> TModel loadModelFromViewModel(TViewModel viewModel, Function<TViewModel, Integer> idFetcher,
                                                                     Function<Integer, TModel> modelLoader) throws NotExistsException {
        Preconditions.throwIllegalArgumentIfParamIsNull(viewModel,"viewModel");
        Integer modelId = idFetcher.apply(viewModel);
        Preconditions.throwIllegalArgumentIfFalse(modelId > 0, String.format("Id of %s is not valid", viewModel.getClass().getName()));
        TModel targetModel = modelLoader.apply(modelId);
        Preconditions.throwNotExistsIfNull(targetModel,targetModel.getClass().getName());
        return targetModel;
    }
}
